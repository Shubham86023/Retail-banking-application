package com.skjs.customerservice.Model.DTO;


import com.skjs.customerservice.Model.Enums.Customer.Gender;
import com.skjs.customerservice.Model.Enums.Customer.MaritalStatus;
import com.skjs.customerservice.Model.Enums.Customer.Relation;
import com.skjs.customerservice.Model.Enums.Validation.EnumValid;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Customer {

    private String customerId;

    @NotBlank(message = "First Name should not be NULL or Blank.")
    private String firstName;

    @NotBlank(message = "Last Name should not be NULL or Blank.")
    private String lastName;

    @NotBlank(message = "Date Of Birth should not be NULL or Blank.")
    private String dateOfBirth;

    @EnumValid(enumClass = Gender.class, message = "Invalid Gender value.")
    @NotNull(message = "Gender should not be NULL.")
    private Gender gender;

    @NotBlank(message = "Mother Name should not be NULL or Blank.")
    private String motherName;

    @EnumValid(enumClass = Relation.class, message = "Invalid Relation value.")
    @NotNull(message = "Relation should not be NULL.")
    private Relation relation;

    @NotBlank(message = "Relative Name should not be NULL or Blank.")
    private String relativeName;

    @EnumValid(enumClass = MaritalStatus.class, message = "Invalid MaritalStatus value.")
    @NotNull(message = "MaritalStatus should not be NULL.")
    private MaritalStatus maritalStatus;

    @NotEmpty(message = "Contact number cannot be empty")
    @Pattern(regexp = "^[0-9]*$", message = "Contact number must contain only digits")
    @Size(min = 10, max = 10, message = "Contact number must be exactly 10 digits")
    private String contactNumber;

    @Pattern(regexp = "^$|^[0-9]{10}$", message = "Sec. contact number must be empty or contain exactly 10 digits.")
    private String secondaryContactNumber;

    @Email(message = "Invalid Email")
    @Nullable
    private String emailId;

    @Valid
    private Address addressData;

    @Valid
    private AllUniqueId uniqueIdData;

    private String imageUrl;
    private String occupation;

}
