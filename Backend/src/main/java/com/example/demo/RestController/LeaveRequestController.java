package com.example.demo.RestController;

import com.example.demo.Service.LeaveRequestService;
import com.example.demo.entities.LeaveRequest;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) throws Exception {
        LeaveRequest createdRequest = leaveRequestService.createLeaveRequest(leaveRequest, leaveRequest.getEmployee().getId());
        return ResponseEntity.ok(createdRequest);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeaveRequest(@PathVariable Long id, @RequestParam UUID approverId) {
        LeaveRequest approvedRequest = leaveRequestService.approveLeaveRequest(id, approverId, "Approved");
        return ResponseEntity.ok(approvedRequest);
    }
    //http://localhost:8080/api/leave-requests/1/reject?rejecterId=1

    @PostMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeaveRequest(@PathVariable Long id, @RequestParam UUID rejecterId) {
        LeaveRequest rejectedRequest = leaveRequestService.rejectLeaveRequest(id, rejecterId, "Rejected");
        return ResponseEntity.ok(rejectedRequest);
    }

    @GetMapping("/{id}/approver")
    public ResponseEntity<User> getLeaveRequestApprover(@PathVariable Long id) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id);
        if (leaveRequest == null) {
            return ResponseEntity.notFound().build();
        }
        User approve = leaveRequestService.getNextApprove(leaveRequest.getEmployee());
        return ResponseEntity.ok(approve);
    }
}