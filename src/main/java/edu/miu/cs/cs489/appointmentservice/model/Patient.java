package edu.miu.cs.cs489.appointmentservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
//    private String address;
    private LocalDate dob;
    private boolean hasUnpaidBill;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", unique = true, nullable = true)
//    @JsonIgnore
    @JsonManagedReference
    private Address primaryAddress;

}

