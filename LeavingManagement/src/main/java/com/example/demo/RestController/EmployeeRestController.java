package com.example.demo.RestController;

import com.example.demo.ServiceContract.IServiceEmployee;
import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class EmployeeRestController {
    private IServiceEmployee _serviceEmployee;
    @PostMapping("/addEmployee")
    public User addEmployee(@RequestBody User employee){
        return _serviceEmployee.addEmployee(employee);
    }
    @GetMapping("/getEmployee/{idEmployee}")
    public User getByIdEmployee(@PathVariable UUID idEmployee){
        return _serviceEmployee.getEmployeeById(idEmployee);
    }
    @GetMapping("/getAllEmployee")
    public List<User> getByIdEmployee(){
        return _serviceEmployee.getAllEmployee();
    }
}
