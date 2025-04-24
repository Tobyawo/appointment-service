package edu.miu.cs.cs489.appointmentservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;


public record AppointmentRescheduleRequestDTO(

        @NotNull(message = "Appointment date is required")
        LocalDate date,

        @NotNull(message = "Appointment time is required")
        LocalTime time


) {}
