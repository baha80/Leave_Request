package com.example.demo.Repository;

import com.example.demo.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Object> findById(UUID teamId);
}
