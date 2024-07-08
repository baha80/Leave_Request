package com.example.demo.RestController;

import com.example.demo.Service.IServiceEmployee;
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

    //give me json to test this : {"id":1,"name":"John","email":"
    @GetMapping("/getEmployee/{idEmployee}")
    public User getByIdEmployee(@PathVariable UUID idEmployee){
        return _serviceEmployee.getEmployeeById(idEmployee);
    }
    @GetMapping("/getAllEmployee")
    public List<User> getByIdEmployee(){
        return _serviceEmployee.getAllEmployee();
    }


    /*@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApprovalChainService approvalChainService;

    @GetMapping("/{userId}/approver")
    public ResponseEntity<User> getApprover(@PathVariable UUID userId) {
        User employee = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        User approver = approvalChainService.getNextApprover(employee);
        return ResponseEntity.ok(approver);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable UUID userId) {
        return userRepository.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}*/
}
