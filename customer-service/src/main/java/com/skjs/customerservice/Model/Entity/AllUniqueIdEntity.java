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
@Document(collection = "col_allUniqueId")
public class AllUniqueIdEntity implements Serializable {

    @Id
    private String uniqueId;

    private String aadharCardNumber;
    private String panCardNumber;
    private String voterIdCardNumber;
}
