package com.example.demo.Service;

import com.example.demo.Enum.Roles;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.LeaveRequestRepository;
import com.example.demo.entities.Enum.LeaveStatus;
import com.example.demo.entities.LeaveRequest;
import com.example.demo.entities.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class LeaveRequestService {

    ApprovalChainService approvalChainService;
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    @Autowired
    private LeaveBalanceService leaveBalanceService;
    @Autowired
    private EmailService emailService;
    private EmployeeRepository userService;

//    @Transactional
//    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest, UUID employeeId) {
//        log.info("Creating leave request for employee ID: {}", employeeId);
//
//        Optional<User> employee = userService.findById(employeeId);
//        int daysRequested = calculateDays(leaveRequest.getStartDate(), leaveRequest.getEndDate());
//
//        if (!leaveBalanceService.hasEnoughBalance(employeeId, leaveRequest.getLeaveType(), daysRequested)){
//            throw new IllegalStateException("Insufficient leave balance");
//        }
//
//        leaveRequest.setEmployee(employee.get());
//        leaveRequest.setStatus(LeaveStatus.PENDING);
//        leaveRequest.setDurationDays(daysRequested);
//
//        LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);
//
//       // User approver = getNextApprover(employee.get());
////        emailService.sendNotification(approver.getEmail(), "New Leave Request",
////                "A new leave request has been submitted by " + employee.get().getUsername());
//
//        log.info("Leave request created successfully for employee ID: {}", employeeId);
//        return savedRequest;
//    }

    @Transactional
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest, UUID employeeId) {
        log.info("Creating leave request for employee ID: {}", employeeId);

        User employee = userService.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        int daysRequested = calculateDays(leaveRequest.getStartDate(), leaveRequest.getEndDate());

        if (!leaveBalanceService.hasEnoughBalance(employeeId, leaveRequest.getLeaveType(), daysRequested)){
            throw new IllegalStateException("Insufficient leave balance");
        }

        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(LeaveStatus.PENDING);
        leaveRequest.setDurationDays(daysRequested);

        LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);

        User approver = approvalChainService.getNextApprover(employee);
      //  emailService.sendNotification(approver.getEmail(), "New Leave Request",
          //      "A new leave request has been submitted by " + employee.getUsername());

        log.info("Leave request created successfully for employee ID: {}", employeeId);
        return savedRequest;
    }

    @Transactional
    public LeaveRequest approveLeaveRequest(Long requestId, UUID approverId, String comments) {
        LeaveRequest request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
        Optional<User> approver = userService.findById(approverId);

        if (!canApprove(approver, request)) {
            throw new IllegalStateException("Not authorized to approve this request");
        }

        updateLeaveRequestStatus(request, approver, comments);

        LeaveRequest updatedRequest = leaveRequestRepository.save(request);

        if (request.getStatus() == LeaveStatus.APPROVED || request.getStatus() == LeaveStatus.APPROVED_BY_MANAGER || request.getStatus() == LeaveStatus.APPROVED_BY_TEAM_LEAD) {
            leaveBalanceService.deductLeaveBalance(request.getEmployee().getId(),
                    request.getLeaveType(),
                    request.getDurationDays());
        }

       // sendNotifications(updatedRequest);

        return updatedRequest;
    }

    @Transactional
    public LeaveRequest rejectLeaveRequest(Long requestId, UUID rejecterId, String comments) {
        LeaveRequest request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
        Optional<User> rejector = userService.findById(rejecterId);

        if (!canApprove(Optional.ofNullable(rejector.orElse(null)), request)) {
            throw new IllegalStateException("Not authorized to reject this request");
        }

        request.setStatus(LeaveStatus.REJECTED);
        request.setApproverComments(comments);

        LeaveRequest updatedRequest = leaveRequestRepository.save(request);

        //emailService.sendNotification(request.getEmployee().getEmail(), "Leave Request Rejected",
              //  "Your leave request has been rejected. Comments: " + comments);

        return updatedRequest;
    }

    private boolean canApprove(Optional<User> approver, LeaveRequest request) {
        switch (approver.get().getRoles()) {
            case TEAM_LEAD:
                return request.getStatus() == LeaveStatus.PENDING;
            case Manager:
                return request.getStatus() == LeaveStatus.PENDING ||
                        request.getStatus() == LeaveStatus.APPROVED_BY_TEAM_LEAD;
            case Rh:
                return request.getStatus() == LeaveStatus.APPROVED_BY_MANAGER;
            default:
                return false;
        }
    }

    private void updateLeaveRequestStatus(LeaveRequest request, Optional<User> approver, String comments) {
        switch (approver.get().getRoles()) {
            case TEAM_LEAD:
                request.setStatus(LeaveStatus.APPROVED_BY_TEAM_LEAD);
                break;
            case Manager:
                request.setStatus(LeaveStatus.APPROVED_BY_MANAGER);
                break;
            case Rh:
                request.setStatus(LeaveStatus.APPROVED);
                break;
        }
        request.setApproverComments(comments);
    }

    private User getNextApprover(User employee) {
        if (employee.getRoles() == Roles.TEAM_LEAD) {
           //return employee.getRoles() == Roles.Manager;
            //return userService.findManagerByRole(UserRole.MANAGER)
        } else {
            //return userService.findManagerByRole(Roles.MANAGER);
        }
        return  null;
    }

//

    private void sendNotifications(LeaveRequest request) {
        String subject = "Leave Request Update";
        String message = "Your leave request status has been updated to: " + request.getStatus();
        emailService.sendNotification(request.getEmployee().getEmail(), subject, message);

        User nextApprover = approvalChainService.getNextApprover(request.getEmployee());
        if (nextApprover != null) {
            emailService.sendNotification(nextApprover.getEmail(), "Leave Request Needs Approval",
                    "A leave request by " + request.getEmployee().getUsername() + " needs your approval");
        }
    }

    private int calculateDays(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    //get all leave request by employee kk qq
    public List<LeaveRequest> getLeaveRequestByEmployee(UUID employeeId) {
        return leaveRequestRepository.findByEmployee_Id(employeeId);
    }
    //
}