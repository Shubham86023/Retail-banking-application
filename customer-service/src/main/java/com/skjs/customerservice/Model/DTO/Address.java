package com.skjs.customerservice.Model.DTO;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Address {

    private String addressId;

    @NotEmpty(message = "First Address Line should not be Empty.")
    @NotBlank(message = "First Address Line should not be NULL or Blank.")
    private String firstLine;

    @Nullable
    private String secondLine;

    @NotEmpty(message = "PinCode cannot be empty")
    @Pattern(regexp = "^[0-9]*$", message = "PinCode must contain only digits")
    @Size(min = 6, max = 9, message = "PinCode must be 6-9 digits")
    private String pinCode;

    @NotEmpty(message = "District cannot be empty")
    @Pattern(regexp = "^[0-9]*$", message = "District must contain only digits")
    @Size(min = 1, max = 5, message = "District must be 1-5 digits")
    @Min(value = 1)
    private String districtId;

    @NotEmpty(message = "State cannot be empty")
    @Pattern(regexp = "^[0-9]*$", message = "State must contain only digits")
    @Size(min = 1, max = 5, message = "State must be 1-5 digits")
    @Min(value = 1)
    private String stateId;

}
