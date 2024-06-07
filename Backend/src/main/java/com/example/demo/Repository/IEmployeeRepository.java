package com.example.demo.Repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<User, UUID> {
}
