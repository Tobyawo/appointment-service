package edu.miu.cs.cs489.appointmentservice.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AppointmentRequestDTO(

        @NotBlank(message = "Patient ID is required")
        String patientId,

        @NotBlank(message = "Dentist ID is required")
        Long dentistId,

        @NotNull(message = "Appointment date is required")
        LocalDate date,

        @NotNull(message = "Appointment time is required")
        LocalTime time,

        @NotBlank(message = "Surgery ID is required")
        String surgeryId

) {}
