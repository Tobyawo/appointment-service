package edu.miu.cs.cs489.appointmentservice.dto.response;


import edu.miu.cs.cs489.appointmentservice.dto.request.AddressRequestDTO;

public record PatientResponseDTO(
        String patientId,
        String firstName,
        String lastName,
        String phone,
        AddressRequestDTO primaryAddress
) {


    public PatientResponseDTO(String patientId, String firstName, String lastName) {
        this(patientId, firstName, lastName, "", null);
    }
}

