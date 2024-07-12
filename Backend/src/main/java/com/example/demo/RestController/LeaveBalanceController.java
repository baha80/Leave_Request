package com.example.demo.RestController;


import com.example.demo.Service.LeaveBalanceService;
import com.example.demo.entities.LeaveBalance;
import com.example.demo.entities.Enum.LeaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Replace with your frontend's domain

@RequestMapping("/api/leave-balances")
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    @Autowired
    public LeaveBalanceController(LeaveBalanceService leaveBalanceService) {
        this.leaveBalanceService = leaveBalanceService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<LeaveBalance> getLeaveBalance(@PathVariable UUID userId) {
        LeaveBalance balance = leaveBalanceService.getLeaveBalance(userId);
        return ResponseEntity.ok(balance);
    }//http://localhost:8080/api/leave-balances/1

    //getVacationDays
    @GetMapping("/{userId}/vacation-days")
    public int getVacationDays(@PathVariable UUID userId) {
        int vacationDays = leaveBalanceService.getVacationDays(userId);
        return vacationDays;
    }

    @PostMapping("/initialize")
    public ResponseEntity<LeaveBalance> initializeLeaveBalance(@RequestParam UUID userId,
                                                               @RequestParam int vacationDays,
                                                               @RequestParam int sickDays,
                                                               @RequestParam int personalDays) {
        LeaveBalance balance = leaveBalanceService.initializeLeaveBalance(userId, vacationDays, sickDays, personalDays);
        return ResponseEntity.ok(balance);
    }

    @PutMapping("/{userId}/add")
    public ResponseEntity<LeaveBalance> addLeaveBalance(@PathVariable UUID userId,
                                                        @RequestParam LeaveType leaveType,
                                                        @RequestParam int days) {
        leaveBalanceService.addLeaveBalance(userId, leaveType, days);
        LeaveBalance updatedBalance = leaveBalanceService.getLeaveBalance(userId);
        return ResponseEntity.ok(updatedBalance);
    }

    @PutMapping("/{userId}/deduct")
    public ResponseEntity<LeaveBalance> deductLeaveBalance(@PathVariable UUID userId,
                                                           @RequestParam LeaveType leaveType,
                                                           @RequestParam int days) {
        leaveBalanceService.deductLeaveBalance(userId, leaveType, days);
        LeaveBalance updatedBalance = leaveBalanceService.getLeaveBalance(userId);
        return ResponseEntity.ok(updatedBalance);
    }

    @PutMapping("/{userId}/reset")
    public ResponseEntity<LeaveBalance> resetYearlyBalance(@PathVariable UUID userId,
                                                           @RequestParam int vacationDays,
                                                           @RequestParam int sickDays,
                                                           @RequestParam int personalDays) {
        leaveBalanceService.resetYearlyBalance(userId, vacationDays, sickDays, personalDays);
        LeaveBalance updatedBalance = leaveBalanceService.getLeaveBalance(userId);
        return ResponseEntity.ok(updatedBalance);
    }
}
