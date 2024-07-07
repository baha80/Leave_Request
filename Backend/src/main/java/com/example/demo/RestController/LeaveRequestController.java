package com.example.demo.RestController;

import com.example.demo.Service.LeaveRequestService;
import com.example.demo.entities.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/create")
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) throws Exception {
        System.out.println("Leave Request: " + leaveRequest);
        return leaveRequestService.createLeaveRequest(leaveRequest, leaveRequest.getEmployee().getId());
    }

    //give me json to test this : {"id":1,"employee":{"id":1},"leaveType":"Sick","startDate":"2021-09-01","endDate":"2021-09-02","status":"Pending"}

    @PostMapping("/{id}/approve")
  public LeaveRequest approveLeaveRequest(@PathVariable Long id) {
            return leaveRequestService.approveLeaveRequest(id , UUID.randomUUID(), "Approved");
        }

    @PostMapping("/{id}/reject")
    public LeaveRequest rejectLeaveRequest(@PathVariable Long id) {
        return leaveRequestService.rejectLeaveRequest(id, UUID.randomUUID(), "Rejected");
    }
}
