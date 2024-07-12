package com.example.demo.Service;

import com.example.demo.Enum.Roles;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.TeamRepository;
import com.example.demo.entities.Team;
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

    TeamRepository teamRepository;

    @Override
    public User addEmployee(User employee) {
        //set field leaveBalance to 0
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

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }



//    @Override
//    public User addUserToTeam(UUID userId, UUID teamId, String role) {
//        User user = employeeRepository.findById(userId).orElse(null);
//        Team team = (Team) teamRepository.findById(teamId).orElse(null);
//        if(user != null && team != null){
//            user.setTeam(team);
//            user.setRoles(Roles.valueOf(role));
//            return employeeRepository.save(user);
//        }
//        return null;
//    }

    @Override
    public User addUserToTeam(UUID userId, UUID teamId, String role) {
        User user = employeeRepository.findById(userId).orElse(null);
        Team team = (Team) teamRepository.findById(teamId).orElse(null);

        if (user != null && team != null) {
            user.setTeam(team);
            user.setRoles(Roles.valueOf(role));

            // Update the team lead or manager if the role matches
            if ("TEAM_LEAD".equals(role)) {
                team.setTeamLead(user);
            } else if ("Manager".equals(role)) {
                team.setUser(user);
            }

            teamRepository.save(team);  // Save the updated team entity
            return employeeRepository.save(user);  // Save the updated user entity
        }

        return null;
    }



}
