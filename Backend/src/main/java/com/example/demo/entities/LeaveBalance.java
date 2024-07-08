package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int vacationDays;
    private int sickDays;
    private int personalDays;



}