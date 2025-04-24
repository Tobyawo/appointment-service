package edu.miu.cs.cs489.appointmentservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Dentists")
@Data
@RequiredArgsConstructor
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dentistId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String specialization;

    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments = new ArrayList<>();



    public Dentist(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

