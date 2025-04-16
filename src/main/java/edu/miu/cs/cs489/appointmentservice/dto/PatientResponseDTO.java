package edu.miu.cs.cs489.appointmentservice.dto;


public record PatientResponseDTO(
        Long patientId,
        String firstName,
        String lastName,
        String phone,
        AddressRequestDTO primaryAddress
) {}

