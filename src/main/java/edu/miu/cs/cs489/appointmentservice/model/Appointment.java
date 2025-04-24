package edu.miu.cs.cs489.appointmentservice.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Appointments")
@Data
@RequiredArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private LocalDate date;
    private LocalTime time;
    private String status;

    @ManyToOne
    private Dentist dentist;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Surgery surgery;

    public Appointment(LocalDate date, LocalTime time, String status, Dentist dentist, Patient patient, Surgery surgery) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.dentist = dentist;
        this.patient = patient;
        this.surgery = surgery;
    }
}
