package com.example.demo.Repository;

import com.example.demo.Enum.Roles;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<User, UUID> {

    //find employee by role
    User findByRoles(Roles role);
}
