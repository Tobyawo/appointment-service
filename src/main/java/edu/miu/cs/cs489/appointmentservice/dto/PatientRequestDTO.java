package edu.miu.cs.cs489.appointmentservice.dto;


public record PatientRequestDTO(
        String firstName,
        String lastName,
        String phone,
        AddressRequestDTO primaryAddress
) {}

