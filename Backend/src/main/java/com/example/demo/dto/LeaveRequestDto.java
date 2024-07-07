package com.example.demo.dto;

import com.example.demo.entities.Enum.LeaveType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.validation.constraints.Future;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class LeaveRequestDto {
    private UUID id;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotNull(message = "Reason is required")
    private String reason;

    private String status;

    @NotNull(message = "Leave type is required")
    private LeaveType leaveType;

    private boolean managerApproved;
    private boolean hrApproved;
    private String managerComments;
    private String hrComments;
    private int durationDays;
}