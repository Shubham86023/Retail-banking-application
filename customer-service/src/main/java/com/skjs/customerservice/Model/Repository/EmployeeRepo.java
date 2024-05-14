package com.skjs.customerservice.Model.Repository;

import com.skjs.customerservice.Model.Entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends MongoRepository<EmployeeEntity,String> {

    Optional<EmployeeEntity> findByUsername(String username);
}
