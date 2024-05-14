package com.skjs.customerservice.Service;

import com.skjs.customerservice.Model.DTO.Customer;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomer(String customerId);

    Customer updateCustomer(Customer customer, String customerId);

    Customer addCustomer(Customer customer);

    boolean deleteCustomer(@PathVariable String customerId);

}
