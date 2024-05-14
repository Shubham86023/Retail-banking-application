package com.skjs.customerservice.Model.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skjs.customerservice.Model.Enums.Customer.Gender;
import com.skjs.customerservice.Model.Enums.Customer.MaritalStatus;
import com.skjs.customerservice.Model.Enums.Customer.Relation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "col_customer")
public class CustomerEntity implements Serializable {

    @Id
    private String customerId;

    @JsonProperty("first_name")
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    private Gender gender;

    private String motherName;
    private Relation relation;
    private String relativeName;
    private MaritalStatus maritalStatus;

    private String contactNumber;
    private String secondaryContactNumber;
    private String emailId;

    @DBRef
    private AddressEntity addressData;

    @DBRef
    private AllUniqueIdEntity uniqueIdData;

    private String imageUrl;
    private String occupation;
}
