package edu.miu.cs.cs489.appointmentservice.model;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
