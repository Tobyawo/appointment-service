package edu.miu.cs.cs489.appointmentservice.controller;

import edu.miu.cs.cs489.appointmentservice.dto.request.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.PatientResponseDTO;
import edu.miu.cs.cs489.appointmentservice.service.serviceImpl.PatientServiceImpl;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api/v1")
public class AdminController {

    private final PatientServiceImpl patientService;

    public AdminController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatientsSortedByLastName());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable String id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable String id, @RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientRequestDTO));
    }

    @DeleteMapping("/patient/{id}")
    @PreAuthorize("hasAnyRole('ADMIN') or hasAuthority('ADMIN_READ')")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/search")
    public ResponseEntity<List<PatientResponseDTO>> searchPatients(@RequestParam("searchString") @NotBlank String searchString) {
        return ResponseEntity.ok(patientService.searchPatients(searchString));
    }
}
