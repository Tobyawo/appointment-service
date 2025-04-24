package edu.miu.cs.cs489.appointmentservice.dto.response;

import java.time.LocalDate;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponseDTO(
        Long appointmentId,
        LocalDate date,
        LocalTime time,
        String status,
        String patientName,
        String dentistName,
        String surgeryName
) {}
