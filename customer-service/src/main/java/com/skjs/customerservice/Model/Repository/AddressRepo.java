package com.skjs.customerservice.Model.Repository;

import com.skjs.customerservice.Model.Entity.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends MongoRepository<AddressEntity, String> {

    void deleteByAddressId(String addressId);
}
