package edu.miu.cs.cs489.appointmentservice.dto.request;


public record PatientRequestDTO(
        String patientId,
        String firstName,
        String lastName,
        String phone,
        AddressRequestDTO primaryAddress
) {
    public PatientRequestDTO(String patientId, String firstName, String lastName) {
        this(patientId, firstName, lastName, "", null);
    }
}

