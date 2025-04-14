package edu.miu.cs.cs489.appointmentservice.repository;

import edu.miu.cs.cs489.appointmentservice.model.Surgery;
import edu.miu.cs.cs489.appointmentservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
