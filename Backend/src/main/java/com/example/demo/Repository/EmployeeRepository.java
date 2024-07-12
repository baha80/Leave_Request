package com.example.demo.Repository;

import com.example.demo.Enum.Roles;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<User, UUID> {

    //find employee by role
    User findByRoles(Roles role);

    @Query("SELECT t.teamLead FROM Team t JOIN t.members m WHERE m.id = :userId")
    User findTeamLeadByUserId(UUID userId);

    @Query("SELECT t.user FROM Team t JOIN t.members m WHERE m.id = :userId")
    User findManagerByUserId(UUID userId);
}
