package com.example.demo.Service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceEmployee implements IServiceEmployee{

    EmployeeRepository employeeRepository; //why employeeRepository is never assigned ? :

    @Override
    public User addEmployee(User employee) {
      return  employeeRepository.save(employee);
    }

    @Override
    public User getEmployeeById(@NotNull UUID id) {
        return  employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
