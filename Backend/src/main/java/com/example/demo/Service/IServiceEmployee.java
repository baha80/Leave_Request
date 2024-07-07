package com.example.demo.Service;

import com.example.demo.entities.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface IServiceEmployee  {

    public User addEmployee(User employee);
    public User getEmployeeById(@NotNull UUID id);
    public List<User> getAllEmployee();
}
