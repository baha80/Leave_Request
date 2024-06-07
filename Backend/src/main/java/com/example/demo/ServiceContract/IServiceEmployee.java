package com.example.demo.ServiceContract;

import com.example.demo.entities.User;

import java.util.List;
import java.util.UUID;

public interface IServiceEmployee {

    public User addEmployee(User employee);
    public User getEmployeeById(UUID id);
    public List<User> getAllEmployee();
}
