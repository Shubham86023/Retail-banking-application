package com.skjs.customerservice.Model.Repository;

import com.skjs.customerservice.Model.Entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends MongoRepository<CustomerEntity, String> {

    @Query("{customerId : ?0}")
    Optional<CustomerEntity> findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);
}
