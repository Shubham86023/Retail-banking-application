package com.skjs.customerservice.Config.Security;

import com.skjs.customerservice.Model.Entity.EmployeeEntity;
import com.skjs.customerservice.Model.Repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EmployeeEntity> entity = employeeRepo.findByUsername(username);
        if(entity.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new EmployeeDetails(entity.get());
    }
}
