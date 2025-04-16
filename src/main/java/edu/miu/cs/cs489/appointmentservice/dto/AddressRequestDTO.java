package edu.miu.cs.cs489.appointmentservice.dto;

public record AddressRequestDTO(
        String street,
        String city,
        String state,
        String zip
) {}

