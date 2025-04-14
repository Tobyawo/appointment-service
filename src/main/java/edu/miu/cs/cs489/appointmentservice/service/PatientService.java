package edu.miu.cs.cs489.appointmentservice.service;

import edu.miu.cs.cs489.appointmentservice.model.Patient;
import edu.miu.cs.cs489.appointmentservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepo;

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(String id) {
        return patientRepo.findById(id).orElse(null);
    }

    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }

    public void delete(String id) {
        patientRepo.deleteById(id);
    }
}
