package com.example.demo.entities;


import com.example.demo.entities.Enum.LeaveStatus;
import com.example.demo.entities.Enum.LeaveType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
//import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User employee;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    private String approverComments;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    private int durationDays;
    @JsonIgnore
    @ManyToOne
    private LeaveBalance leaveBalance;



}



