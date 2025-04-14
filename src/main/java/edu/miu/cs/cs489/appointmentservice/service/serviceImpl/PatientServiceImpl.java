package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;

import edu.miu.cs.cs489.appointmentservice.model.Patient;
import edu.miu.cs.cs489.appointmentservice.repository.PatientRepository;
import edu.miu.cs.cs489.appointmentservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
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
