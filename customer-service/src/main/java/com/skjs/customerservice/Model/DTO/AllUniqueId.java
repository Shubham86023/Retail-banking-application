package com.skjs.customerservice.Model.DTO;


import com.skjs.customerservice.Model.DTO.IdValidator.AtLeastOneNotNull;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
@AtLeastOneNotNull(message = "All identification numbers should not be blank or NULL, provide atleast one.")
public class AllUniqueId {

    private String uniqueId;

    @Nullable
    private String aadharCardNumber;

    @Nullable
    private String panCardNumber;

    @Nullable
    private String voterIdCardNumber;

}
