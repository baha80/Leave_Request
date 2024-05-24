package com.example.demo.Service;

import com.example.demo.Repository.IEmployeeRepository;
import com.example.demo.ServiceContract.IServiceEmployee;
import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceEmployee implements IServiceEmployee {
    private IEmployeeRepository _employeeRepository;

    @Override
    public User addEmployee(User employee) {
      return  _employeeRepository.save(employee);
    }

    @Override
    public User getEmployeeById(UUID id) {
        return  _employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllEmployee() {
        return _employeeRepository.findAll();
    }
}
