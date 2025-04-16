package edu.miu.cs.cs489.appointmentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "surgeries")
@Data
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surgeryId;
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments = new ArrayList<>();
}

