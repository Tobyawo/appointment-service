package edu.miu.cs.cs489.appointmentservice.dto;

public record AddressResponseDTO(
        Long id,
        String street,
        String city,
        String state,
        String zip,
        PatientResponseDTO2 patient
) {}

