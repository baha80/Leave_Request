package com.example.demo.entities;

import com.example.demo.Enum.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
//added now

    // Add leaveBalance field with default value
    @Column(name = "leaveBalance", nullable = false, columnDefinition = "int default 0")
    private int leaveBalance = 0;
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;
//    private String username;
//    private String password;
//    private String email;
//    @Enumerated(EnumType.STRING)
//    private Roles roles;
//    @OneToMany
//    public Set<Team> teams;
//    //added solde de conges
//    //private int leaveBalance;
//    private int leavebalence ;
//    @ManyToOne
//    private Team team;
//    @ManyToOne
//    private User manager;
//    @OneToMany(mappedBy = "employee")
//    private List<LeaveRequest> leaveRequests;
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private LeaveBalance leaveBalance;
//
//    @Column(name = "leavebalence", nullable = false)
//    private int leavebalence = 0; // Set a default value
////NEw
//
////    @ManyToOne
////    private Team team;
////
////    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
////    private LeaveBalance leaveBalance;
//
//// Remove these lines
//// public Set<Team> teams;
//// private int leavebalence;
//
//
//
//    @OneToMany(mappedBy = "manager")
//    private Set<User> subordinates;
}