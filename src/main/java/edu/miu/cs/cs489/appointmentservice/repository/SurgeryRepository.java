package edu.miu.cs.cs489.appointmentservice.repository;

import edu.miu.cs.cs489.appointmentservice.model.Patient;
import edu.miu.cs.cs489.appointmentservice.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepository extends JpaRepository<Surgery, String> {
}
