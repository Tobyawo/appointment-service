package edu.miu.cs.cs489.appointmentservice.repository;

import edu.miu.cs.cs489.appointmentservice.model.Dentist;
import edu.miu.cs.cs489.appointmentservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository extends JpaRepository<Dentist, String> {
}
