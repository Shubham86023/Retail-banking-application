package com.skjs.customerservice.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "col_address")
public class AddressEntity implements Serializable {

    @Id
    private String addressId;

    private String firstLine;
    private String secondLine;
    private String pinCode;
    private String districtId;
    private String stateId;

}
