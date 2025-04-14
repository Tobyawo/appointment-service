package edu.miu.cs.cs489.appointmentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Roles")
@Data
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
