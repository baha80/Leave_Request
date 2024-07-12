package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String description;

    @OneToOne
    @JoinColumn(name = "team_lead_id")
    private User teamLead;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User user;

    @OneToMany(mappedBy = "team")
    private Set<User> members;

//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "id", updatable = false, nullable = false)
//    private UUID id;
//    private String Name;
//    private String Description;
////    @ManyToOne
////    public  User user;
//
//    @OneToOne
//    private User teamLead;
//
//    @ManyToOne
//    private User user; // This should be the team's manager
//
//    @OneToMany(mappedBy = "team")
//    private Set<User> members;

    //NEW
//    @OneToMany(mappedBy = "team")
//    private Set<User> members;
//
//    @OneToOne
//    private User teamLead;

// Remove this line
// public User user;
}
