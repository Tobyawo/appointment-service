package edu.miu.cs.cs489.appointmentservice.repository;

import edu.miu.cs.cs489.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


//    List<Appointment> findByPatient_PatientId(String patientId);

    List<Appointment> findAppointmentByPatient_PatientId(String patientId);

    @Query("SELECT COUNT(a) FROM Appointments a WHERE a.dentist.dentistId = :dentistId AND FUNCTION('WEEK', a.date) = FUNCTION('WEEK', :date)")
    long countByDentistIdAndWeek(Long dentistId, LocalDate date);

}
