package com.skjs.customerservice.Model.Mapper.Implementation;

import com.skjs.customerservice.Model.DTO.Address;
import com.skjs.customerservice.Model.DTO.AllUniqueId;
import com.skjs.customerservice.Model.DTO.Customer;
import com.skjs.customerservice.Model.Entity.AddressEntity;
import com.skjs.customerservice.Model.Entity.AllUniqueIdEntity;
import com.skjs.customerservice.Model.Entity.CustomerEntity;
import com.skjs.customerservice.Model.Mapper.BaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapper extends BaseMapper<CustomerEntity, Customer> {

    private final AddressMapper addressMapper;
    private final AllUniqueIdMapper allUniqueIdMapper;


    @Override
    public CustomerEntity DtoToEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        if (customer != null) {
            AddressEntity aEnt = addressMapper.DtoToEntity(customer.getAddressData());
            AllUniqueIdEntity uEnt = allUniqueIdMapper.DtoToEntity(customer.getUniqueIdData());
            BeanUtils.copyProperties(customer, entity);
            entity.setAddressData(aEnt);
            entity.setUniqueIdData(uEnt);
        }
        return entity;
    }

    @Override
    public Customer EntityToDto(CustomerEntity customerEntity) {
        Customer dto = new Customer();
        if (customerEntity != null) {
            Address aDto = addressMapper.EntityToDto(customerEntity.getAddressData());
            AllUniqueId uDto = allUniqueIdMapper.EntityToDto(customerEntity.getUniqueIdData());
            BeanUtils.copyProperties(customerEntity, dto);
            dto.setAddressData(aDto);
            dto.setUniqueIdData(uDto);
        }
        return dto;
    }
}
