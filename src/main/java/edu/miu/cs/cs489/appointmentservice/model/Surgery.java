package edu.miu.cs.cs489.appointmentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "surgeries")
@Data
@RequiredArgsConstructor
public class Surgery {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String surgeryId;
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments = new ArrayList<>();

    public Surgery(String surgeryId) {
        this.surgeryId = surgeryId;
    }
}

