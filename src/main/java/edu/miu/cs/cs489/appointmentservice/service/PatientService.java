package edu.miu.cs.cs489.appointmentservice.service;

import edu.miu.cs.cs489.appointmentservice.dto.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    PatientResponseDTO getPatientById(Long id);
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO);
    void deletePatient(Long id);
    List<PatientResponseDTO> getAllPatientsSortedByLastName();
    List<PatientResponseDTO> searchPatients(String searchString);

}
