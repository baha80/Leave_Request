package com.example.demo.Service;

import com.example.demo.Enum.Roles;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalChainService {

    @Autowired
    private EmployeeRepository userRepository;

    public User getNextApprover(User employee) {
        if (employee.getRoles() == Roles.Employee) {
            return employee.getTeam().getTeamLead();
        } else if (employee.getRoles() == Roles.TEAM_LEAD) {
            return employee.getManager();
        } else if (employee.getRoles() == Roles.Manager) {
            return userRepository.findByRoles(Roles.Rh);
        }
        throw new IllegalStateException("No approver found for role: " + employee.getRoles());
    }
}
