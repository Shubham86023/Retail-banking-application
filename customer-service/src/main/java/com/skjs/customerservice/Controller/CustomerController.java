package com.skjs.customerservice.Controller;

import com.skjs.customerservice.Model.DTO.Customer;
import com.skjs.customerservice.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-management")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomers() {
        logger.info("Request to get All customer is initiated.");
        long beginTime = System.currentTimeMillis();
        List<Customer> lis = customerService.getAllCustomers();
        logger.info("Time required to finish current request: {}", System.currentTimeMillis() - beginTime);
        return ResponseEntity.ok(lis);
    }

    //http://employee_service/customer-management/customer/2
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable String customerId) {
        logger.info("Request to get a customer Details is initiated.");
        long beginTime = System.currentTimeMillis();
        Customer customer = customerService.getCustomer(customerId);
        logger.info("Time required to finish current request: {}", System.currentTimeMillis() - beginTime);

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody @Valid Customer customer) {

        Customer output = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }


    @PutMapping("/customer/{customerId}")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid Customer customer, @PathVariable String customerId) {
        Customer output = customerService.updateCustomer(customer, customerId);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body("Record deleted successfully.");
    }


}
