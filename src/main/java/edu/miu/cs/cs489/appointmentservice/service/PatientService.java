package edu.miu.cs.cs489.appointmentservice.service;

import edu.miu.cs.cs489.appointmentservice.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();
    Patient getPatientById(String id);
    Patient save(Patient patient);
    void delete(String id);
}
