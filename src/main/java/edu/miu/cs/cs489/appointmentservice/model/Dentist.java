package edu.miu.cs.cs489.appointmentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Dentists")
@Data
public class Dentist {
    @Id
    private String dentistId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String specialization;

    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments = new ArrayList<>();
}

