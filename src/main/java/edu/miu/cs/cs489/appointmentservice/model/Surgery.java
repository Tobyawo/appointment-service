package edu.miu.cs.cs489.appointmentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "surgeries")
@Data
@AllArgsConstructor
public class Surgery {
    @Id
    private String surgeryId;
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments = new ArrayList<>();
}

