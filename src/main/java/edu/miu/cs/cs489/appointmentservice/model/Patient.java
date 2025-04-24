package edu.miu.cs.cs489.appointmentservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "patients")
@Data
@RequiredArgsConstructor
public class Patient {
    @Id
    private String patientId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDate dob;
    private boolean hasUnpaidBill;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", unique = true, nullable = true)
//    @JsonIgnore
    @JsonManagedReference
    private Address primaryAddress;

    public Patient(String patientId, String firstName, String lastName) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Patient(String patientId, String firstName, String lastName, String phone, String email, LocalDate dob, boolean hasUnpaidBill, Address primaryAddress) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.hasUnpaidBill = hasUnpaidBill;
        this.primaryAddress = primaryAddress;
    }
}

