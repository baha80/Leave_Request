package com.example.demo.RestController;

import com.example.demo.Service.ApprovalChainService;
import com.example.demo.Service.IServiceEmployee;
import com.example.demo.entities.Team;
import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Replace with your frontend's domain

@AllArgsConstructor
public class EmployeeRestController {
    private IServiceEmployee _serviceEmployee;
    @PostMapping("/addEmployee")
    public User addEmployee(@RequestBody User employee){
        //employee.setLeaveBalance().setVacationDays(10);
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


    //add user t a team
    @PostMapping("/addUserToTeam/{userId}/{teamId}/{role}")
    public User addUserToTeam(@PathVariable UUID userId, @PathVariable UUID teamId, @PathVariable String role){
        return _serviceEmployee.addUserToTeam(userId, teamId, role);
    }
    //http://localhost:8080/addUserToTeam/1/1/Developer

    //create a team
    @PostMapping("/addTeam")
    public Team addTeam(@RequestBody Team team){
        return _serviceEmployee.addTeam(team);
    }


    @Autowired
    private ApprovalChainService approvalChainService;

    @GetMapping("/{userId}/approver")
    public User getApprover(@PathVariable UUID userId) {
        User employee = _serviceEmployee.getEmployeeById(userId);
        if (employee == null) {
            //return ResponseEntity.notFound().build();
            return null;
        }
        User approve = approvalChainService.getNextApprover(employee);
        if (approve == null) {
            //return ResponseEntity.noContent().build();
            return null;
        }
        //return ResponseEntity.ok(approve);//this will return the next approver
        return approve;
    }

    //json to test this : {"id":1,"name":"Team1"}
}
