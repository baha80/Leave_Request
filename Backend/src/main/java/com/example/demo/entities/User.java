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
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @OneToMany
    public Set<Team> teams;
    //added solde de conges
    //private int leaveBalance;

    @ManyToOne
    private Team team;

    @ManyToOne
    private User manager;



    @OneToMany(mappedBy = "employee")
    private List<LeaveRequest> leaveRequests;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private LeaveBalance leaveBalance;
}