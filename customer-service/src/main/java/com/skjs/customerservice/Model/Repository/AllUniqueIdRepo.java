package com.skjs.customerservice.Model.Repository;

import com.skjs.customerservice.Model.Entity.AllUniqueIdEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllUniqueIdRepo extends MongoRepository<AllUniqueIdEntity, String> {

    @Query("{'$or':[{'aadharCardNumber': ?0}, {'panCardNumber': ?1}, {'voterIdCardNumber': ?2}]}")
    List<AllUniqueIdEntity> searchForUniqueIds(String aadhar, String pan, String voter);

    Optional<AllUniqueIdEntity> findByAadharCardNumber(String aadhar);

    Optional<AllUniqueIdEntity> findByPanCardNumber(String pan);

    Optional<AllUniqueIdEntity> findByVoterIdCardNumber(String voter);

    void deleteByUniqueId(String uniqueId);
}


