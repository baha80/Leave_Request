package com.example.demo.RestController;

import com.example.demo.Service.LeaveRequestService;
import com.example.demo.entities.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Replace with your frontend's domain

public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/create")
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) throws Exception {
        System.out.println("Leave Request: " + leaveRequest);
        return leaveRequestService.createLeaveRequest(leaveRequest, leaveRequest.getEmployee().getId());
    }

//    @PostMapping("/create")
//    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest, @RequestParam UUID employeeId) throws Exception {
//        return leaveRequestService.createLeaveRequest(leaveRequest, employeeId);
//    }

    //give me json to test this : {"id":1,"employee":{"id":1},"leaveType":"Sick","startDate":"2021-09-01","endDate":"2021-09-02","status":"Pending"}
//approve leave request

//    @PostMapping("/{id}/approve")
//  public LeaveRequest approveLeaveRequest(@PathVariable Long id) {
//            return leaveRequestService.approveLeaveRequest(id , UUID.randomUUID(), "Approved");
//        }

    @PostMapping("/{id}/approve/{approverId}")
    public LeaveRequest approveLeaveRequest(@PathVariable Long id, @PathVariable UUID approverId) {
        return leaveRequestService.approveLeaveRequest(id, approverId, "Approved");
    }//http://localhost:8080/leave-request/1/approve/1

    @PostMapping("/{id}/reject")
    public LeaveRequest rejectLeaveRequest(@PathVariable Long id) {
        return leaveRequestService.rejectLeaveRequest(id, UUID.randomUUID(), "Rejected");
    }
//getLeaveRequestByEmployee
    @GetMapping("/employee/{id}/leave-requests")
    public List<LeaveRequest> getLeaveRequestByEmployee(@PathVariable UUID id) {
        return leaveRequestService.getLeaveRequestByEmployee(id);
    }

    //http://localhost:8080/employee/1/leave-requests
    //where i add @Json!ignore ? : in the entity class
}
