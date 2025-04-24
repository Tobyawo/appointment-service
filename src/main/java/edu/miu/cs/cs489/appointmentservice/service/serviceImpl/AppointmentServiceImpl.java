package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;

import edu.miu.cs.cs489.appointmentservice.dto.request.AppointmentRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.request.AppointmentRescheduleRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.AppointmentResponseDTO;
import edu.miu.cs.cs489.appointmentservice.model.Appointment;
import edu.miu.cs.cs489.appointmentservice.repository.AppointmentRepository;
import edu.miu.cs.cs489.appointmentservice.repository.DentistRepository;
import edu.miu.cs.cs489.appointmentservice.repository.PatientRepository;
import edu.miu.cs.cs489.appointmentservice.repository.SurgeryRepository;
import edu.miu.cs.cs489.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto) {
        // Validate patient, dentist, surgery exist
        var patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));

        if (patient.isHasUnpaidBill()) {
            throw new IllegalStateException("Patient has unpaid bills and cannot book new appointments.");
        }

        var dentist = dentistRepository.findById(dto.dentistId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid dentist ID"));

        long appointmentsThisWeek = appointmentRepository
                .countByDentistIdAndWeek(dto.dentistId(), dto.date());

        if (appointmentsThisWeek >= 5) {
            throw new IllegalStateException("Dentist already has 5 appointments this week.");
        }

        var surgery = surgeryRepository.findById(dto.surgeryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid surgery ID"));

        var appointment = new Appointment();
//        appointment.setAppointmentId(UUID.randomUUID().toString());
        appointment.setDate(dto.date());
        appointment.setTime(dto.time());
        appointment.setStatus("Scheduled");
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setSurgery(surgery);

        appointmentRepository.save(appointment);

        // Send email confirmation (optional hook here)

        return new AppointmentResponseDTO(
                appointment.getAppointmentId(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getStatus(),
                patient.getFirstName() + " " + patient.getLastName(),
                dentist.getFirstName() + " " + dentist.getLastName(),
                surgery.getName()
        );
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatient(String patientId) {
        return appointmentRepository.findAppointmentByPatient_PatientId(patientId)
                .stream()
                .map(a -> new AppointmentResponseDTO(
                        a.getAppointmentId(),
                        a.getDate(),
                        a.getTime(),
                        a.getStatus(),
                        a.getPatient().getFirstName() + " " + a.getPatient().getLastName(),
                        a.getDentist().getFirstName() + " " + a.getDentist().getLastName(),
                        a.getSurgery().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDentist(String dentistId) {
        return appointmentRepository.findAppointmentByPatient_PatientId(dentistId)
                .stream()
                .map(a -> new AppointmentResponseDTO(
                        a.getAppointmentId(),
                        a.getDate(),
                        a.getTime(),
                        a.getStatus(),
                        a.getPatient().getFirstName() + " " + a.getPatient().getLastName(),
                        a.getDentist().getFirstName() + " " + a.getDentist().getLastName(),
                        a.getSurgery().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        appointment.setStatus("Cancelled");
        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentResponseDTO rescheduleAppointment(Long appointmentId, AppointmentRescheduleRequestDTO rescheduleRequestDTO) {
        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        appointment.setDate(rescheduleRequestDTO.date());
        appointment.setTime(rescheduleRequestDTO.time());
        appointment.setStatus("Rescheduled");
        appointmentRepository.save(appointment);

        return new AppointmentResponseDTO(
                appointment.getAppointmentId(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getStatus(),
                appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName(),
                appointment.getDentist().getFirstName() + " " + appointment.getDentist().getLastName(),
                appointment.getSurgery().getName()
        );
    }
}
