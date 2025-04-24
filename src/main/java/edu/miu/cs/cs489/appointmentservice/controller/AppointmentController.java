package edu.miu.cs.cs489.appointmentservice.controller;

import edu.miu.cs.cs489.appointmentservice.dto.request.AppointmentRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.request.AppointmentRescheduleRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.AppointmentResponseDTO;
import edu.miu.cs.cs489.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@Validated
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody @Valid AppointmentRequestDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDTO));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok().body(appointmentService.getAppointmentsByPatient(patientId));
    }


    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(("/{appointmentId}"))
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long appointmentId, @RequestBody @Valid AppointmentRescheduleRequestDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.rescheduleAppointment(appointmentId, appointmentDTO));
    }

}

