package edu.miu.cs.cs489.appointmentservice.service;

import edu.miu.cs.cs489.appointmentservice.dto.request.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    PatientResponseDTO getPatientById(String id);
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO updatePatient(String id, PatientRequestDTO patientRequestDTO);
    void deletePatient(String id);
    List<PatientResponseDTO> getAllPatientsSortedByLastName();
    List<PatientResponseDTO> searchPatients(String searchString);

}
