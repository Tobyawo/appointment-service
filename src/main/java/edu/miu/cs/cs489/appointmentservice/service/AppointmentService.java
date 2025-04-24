package edu.miu.cs.cs489.appointmentservice.service;

import edu.miu.cs.cs489.appointmentservice.dto.request.AppointmentRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.request.AppointmentRescheduleRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.AppointmentResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO);

    List<AppointmentResponseDTO> getAppointmentsByPatient(String patientId);

    List<AppointmentResponseDTO> getAppointmentsByDentist(String dentistId);

    void cancelAppointment(Long appointmentId);

    AppointmentResponseDTO rescheduleAppointment(Long appointmentId, AppointmentRescheduleRequestDTO rescheduleRequestDTO);
}

