package com.example.demo.Service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.entities.Enum.LeaveType;
import com.example.demo.entities.LeaveBalance;
import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@AllArgsConstructor
@Slf4j
@Service
public class LeaveBalanceService  {

    LeaveBalanceRepository leaveBalanceRepository;

    @Autowired
    private ServiceEmployee userService;

    EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public LeaveBalance getLeaveBalance(UUID userId) {
        User user = userService.getEmployeeById(userId);
        return leaveBalanceRepository.findByUser_Id(user.getId());

    }

    @Transactional
    public boolean hasEnoughBalance(UUID userId, LeaveType leaveType, int requestedDays) {
        LeaveBalance balance = getLeaveBalance(userId);
        switch (leaveType) {
            case VACATION:
                return balance.getVacationDays() >= requestedDays;
            case SICK:
                return balance.getSickDays() >= requestedDays;
            case PERSONAL:
                return balance.getPersonalDays() >= requestedDays;
            default:
                throw new IllegalArgumentException("Invalid leave type");
        }
    }

    @Transactional
    public void deductLeaveBalance(UUID userId, LeaveType leaveType, int days) {
        LeaveBalance balance = getLeaveBalance(userId);
        switch (leaveType) {
            case VACATION:
                if (balance.getVacationDays() < days) {
                    throw new IllegalStateException("Not enough vacation days");
                }
                balance.setVacationDays(balance.getVacationDays() - days);
                break;
            case SICK:
                if (balance.getSickDays() < days) {
                    throw new IllegalStateException("Not enough sick days");
                }
                balance.setSickDays(balance.getSickDays() - days);
                break;
            case PERSONAL:
                if (balance.getPersonalDays() < days) {
                    throw new IllegalStateException("Not enough personal days");
                }
                balance.setPersonalDays(balance.getPersonalDays() - days);
                break;
            default:
                throw new IllegalArgumentException("Invalid leave type");
        }
        leaveBalanceRepository.save(balance);
    }

    @Transactional
    public void addLeaveBalance(UUID userId, LeaveType leaveType, int days) {
        LeaveBalance balance = getLeaveBalance(userId);
        switch (leaveType) {
            case VACATION:
                balance.setVacationDays(balance.getVacationDays() + days);
                break;
            case SICK:
                balance.setSickDays(balance.getSickDays() + days);
                break;
            case PERSONAL:
                balance.setPersonalDays(balance.getPersonalDays() + days);
                break;
            default:
                throw new IllegalArgumentException("Invalid leave type");
        }
        leaveBalanceRepository.save(balance);
    }

    @Transactional
    public void initializeLeaveBalance(User user, int vacationDays, int sickDays, int personalDays) {
        LeaveBalance newBalance = new LeaveBalance();
        newBalance.setUser(user);
        newBalance.setVacationDays(vacationDays);
        newBalance.setSickDays(sickDays);
        newBalance.setPersonalDays(personalDays);
        leaveBalanceRepository.save(newBalance);
    }

    @Transactional
    public void resetYearlyBalance(UUID userId, int vacationDays, int sickDays, int personalDays) {
        LeaveBalance balance = getLeaveBalance(userId);
        balance.setVacationDays(vacationDays);
        balance.setSickDays(sickDays);
        balance.setPersonalDays(personalDays);
        leaveBalanceRepository.save(balance);
    }
}