package edu.miu.cs.cs489.appointmentservice.controller;

import edu.miu.cs.cs489.appointmentservice.dto.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.PatientResponseDTO;
import edu.miu.cs.cs489.appointmentservice.service.serviceImpl.PatientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1")
public class PatientController {

    private final PatientServiceImpl patientService;

    public PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatientsSortedByLastName());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id, @RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientRequestDTO));
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/search/{searchString}")
    public ResponseEntity<List<PatientResponseDTO>> searchPatients(@PathVariable String searchString) {
        return ResponseEntity.ok(patientService.searchPatients(searchString));
    }
}
