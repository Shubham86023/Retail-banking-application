package com.skjs.customerservice.Model.Entity;

import com.skjs.customerservice.Model.Enums.Employee.Role;
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
@Document(collection = "col_employee")
public class EmployeeEntity implements Serializable {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private Role role;
}
