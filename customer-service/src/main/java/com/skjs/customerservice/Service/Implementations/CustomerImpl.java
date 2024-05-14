package com.skjs.customerservice.Service.Implementations;

import com.skjs.customerservice.Exceptions.ExceptionTypes.DatabaseQueryException;
import com.skjs.customerservice.Exceptions.ExceptionTypes.ResourceAlreadyExistsException;
import com.skjs.customerservice.Exceptions.ExceptionTypes.ResourceNotFoundException;
import com.skjs.customerservice.Model.DTO.Customer;
import com.skjs.customerservice.Model.Entity.AddressEntity;
import com.skjs.customerservice.Model.Entity.AllUniqueIdEntity;
import com.skjs.customerservice.Model.Entity.CustomerEntity;
import com.skjs.customerservice.Model.Mapper.Implementation.AddressMapper;
import com.skjs.customerservice.Model.Mapper.Implementation.AllUniqueIdMapper;
import com.skjs.customerservice.Model.Mapper.Implementation.CustomerMapper;
import com.skjs.customerservice.Model.Repository.AddressRepo;
import com.skjs.customerservice.Model.Repository.AllUniqueIdRepo;
import com.skjs.customerservice.Model.Repository.CustomerRepo;
import com.skjs.customerservice.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;
    private final AllUniqueIdRepo allUniqueIdRepo;
    private final Logger logger = LoggerFactory.getLogger(CustomerImpl.class);
    private AddressMapper addressMapper = new AddressMapper();
    private AllUniqueIdMapper allUniqueIdMapper = new AllUniqueIdMapper();
    private CustomerMapper customerMapper = new CustomerMapper(addressMapper, allUniqueIdMapper);

//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;

//    @Value("${spring.kafka.topic.topicSignUp.name}")
//    private String topicName;

    @Override
    public List<Customer> getAllCustomers() {

        List<CustomerEntity> lis = customerRepo.findAll();
        if (!lis.isEmpty()) {
            return customerMapper.EntityListToDtoList(lis);
        }
        throw new ResourceNotFoundException("No Customer Found.");
    }

    @Override
    public Customer getCustomer(String customerId) {
        Optional<CustomerEntity> entity = customerRepo.findByCustomerId(customerId);

        entity.orElseThrow(() -> new ResourceNotFoundException("No Customer found for ID: " + customerId));
        return customerMapper.EntityToDto(entity.get());
    }

    @Override
    public Customer addCustomer(Customer customer) {

        CustomerEntity savedCustomer = saveCustomerRecord(customer);

        processAccountCreation(savedCustomer.getCustomerId());

        logger.info("New Customer has been created" + savedCustomer);
        return customerMapper.EntityToDto(savedCustomer);
    }

    @Override
    @Transactional
    public synchronized boolean deleteCustomer(String customerId) {

        Optional<CustomerEntity> entity = customerRepo.findByCustomerId(customerId);
        entity.orElseThrow(() -> new ResourceNotFoundException("No Customer found for ID: " + customerId));

        try {
            CustomerEntity record = entity.get();
            customerRepo.deleteByCustomerId(customerId);
            addressRepo.deleteByAddressId(record.getAddressData().getAddressId());
            allUniqueIdRepo.deleteByUniqueId(record.getUniqueIdData().getUniqueId());
            return true;
        } catch (Exception e) {
            logger.error("Something went wrong while deleting customer data.");
            System.out.println(e);
        }
        return false;
    }

    @Override
    public Customer updateCustomer(Customer customer, String customerId) {

        Optional<CustomerEntity> entity = customerRepo.findByCustomerId(customerId);
        entity.orElseThrow(() -> new ResourceNotFoundException("No Customer found for ID: " + customerId));

        customer.setCustomerId(customerId);
        customer.getUniqueIdData().setUniqueId(entity.get().getUniqueIdData().getUniqueId());
        customer.getAddressData().setAddressId(entity.get().getAddressData().getAddressId());

        CustomerEntity savedCustomer = saveCustomerRecord(customer);

        logger.info("Customer Details has been updated " + savedCustomer);
        return customerMapper.EntityToDto(savedCustomer);

    }

    @Transactional
    public synchronized CustomerEntity saveCustomerRecord(Customer customer) {

        AllUniqueIdEntity uEnt = allUniqueIdMapper.DtoToEntity(customer.getUniqueIdData());
        checkingUniqueId(uEnt, customer.getCustomerId());
        AllUniqueIdEntity savedIds = allUniqueIdRepo.save(uEnt);

        AddressEntity eEnt = addressMapper.DtoToEntity(customer.getAddressData());
        AddressEntity savedAdd = addressRepo.save(eEnt);

        CustomerEntity cEnt = customerMapper.DtoToEntity(customer);
        cEnt.setAddressData(savedAdd);
        cEnt.setUniqueIdData(savedIds);

        CustomerEntity savedCustomer = customerRepo.save(cEnt);

        if (!savedCustomer.getCustomerId().isEmpty()) {
            return savedCustomer;
        }

        logger.error("Something went wrong while data saving..");
        throw new DatabaseQueryException("Something went wrong while data saving.");
    }


    public void checkingUniqueId(AllUniqueIdEntity uEnt, String customerId) {
        boolean isAadharDup = false, isPanDup = false, isVoterDup = false;
        String isAadhar = "", isPan = "", isVoter = "";
        String aadharNo = "", panNo = "", voterNo = "";

        String uniqueId = customerId != null ? getCustomer(customerId).getUniqueIdData().getUniqueId() : null;
        if (!uEnt.getAadharCardNumber().isEmpty()) {
            Optional<AllUniqueIdEntity> entity = allUniqueIdRepo.findByAadharCardNumber(uEnt.getAadharCardNumber());

            if (entity.isPresent() && !Objects.equals(uniqueId, entity.get().getUniqueId())) {
                isAadharDup = true;
                isAadhar = "Aadhar Card No- ";
                aadharNo = uEnt.getAadharCardNumber() + ", ";
            }
        }

        if (!uEnt.getPanCardNumber().isEmpty()) {
            Optional<AllUniqueIdEntity> entity = allUniqueIdRepo.findByPanCardNumber(uEnt.getPanCardNumber());
            if (entity.isPresent() && !Objects.equals(uniqueId, entity.get().getUniqueId())) {
                isPanDup = true;
                isPan = "Pan Card No- ";
                panNo = uEnt.getPanCardNumber() + ", ";

            }
        }

        if (!uEnt.getVoterIdCardNumber().isEmpty()) {
            Optional<AllUniqueIdEntity> entity = allUniqueIdRepo.findByVoterIdCardNumber(uEnt.getVoterIdCardNumber());
            if (entity.isPresent() && !Objects.equals(uniqueId, entity.get().getUniqueId())) {
                isVoterDup = true;
                isVoter = "VoterID Card No- ";
                voterNo = uEnt.getVoterIdCardNumber();
            }
        }

        if (isAadharDup || isPanDup || isVoterDup) {
            logger.error(String.format("%s %s   %s %s   %s %s   already exists.",
                    isAadhar, aadharNo,
                    isPan, panNo,
                    isVoter, voterNo
            ));
            throw new ResourceAlreadyExistsException("%s %s   %s %s   %s %s   already exists.".formatted(
                    isAadhar, aadharNo,
                    isPan, panNo,
                    isVoter, voterNo
            ));
        }
    }

    private synchronized boolean processAccountCreation(String customerId) {

        boolean output = false;
        try {
            if (customerId != null) {
                //   kafkaTemplate.send(topicName, customerId);
                logger.info("New account process initiated for CUSTOMER ID: " + customerId);
                output = true;
            }
        } catch (Exception e) {
            logger.info("Error while processing new account request.");
        }
        return output;

    }
}
