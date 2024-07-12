//package com.example.demo.Service;
//
//import com.example.demo.Enum.Roles;
//import com.example.demo.Repository.EmployeeRepository;
//import com.example.demo.entities.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//
//@Service
//public class ApprovalChainService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    public User getNextApprover(User employee) {
//        if (employee == null) {
//            return null;
//        }
//
//        if (employee.getTeam() == null) {
//            // If the employee is not in a team, return null or a default approver
//            return null;
//        }
//
//        switch (employee.getRoles()) {
//            case Employee:
//                return employee.getTeam().getTeamLead();
//            case TEAM_LEAD:
//                return employee.getTeam().getUser(); // Assuming this is the team's manager
//            case Manager:
//                // For managers, you might want to return an HR representative or null
//                return employeeRepository.findByRoles(Roles.Rh);
//            default:
//                return null;
//        }
//    }
//}

package com.example.demo.Service;

import com.example.demo.Enum.Roles;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApprovalChainService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public User getNextApprover(User employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getTeam() == null) {
            // If the employee is not in a team, return null or a default approver
            return null;
        }

        UUID userId = employee.getId();
        switch (employee.getRoles()) {
            case Employee:
                User teamLead = employeeRepository.findTeamLeadByUserId(userId);
                return teamLead;
            case TEAM_LEAD:
                User manager = employeeRepository.findManagerByUserId(userId);
                return manager;
            case Manager:
                User hrRep = employeeRepository.findByRoles(Roles.Rh);
                return hrRep;
            default:
                return null;
        }
    }
}
