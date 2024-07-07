package com.example.demo.Service;

import com.example.demo.entities.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long>{

    //find leave balance by user
    LeaveBalance findByUser_Id(UUID id);
}
