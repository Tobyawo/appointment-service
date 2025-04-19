package edu.miu.cs.cs489.appointmentservice.dto;


import edu.miu.cs.cs489.appointmentservice.model.Permission;
import lombok.Data;

import java.util.Set;

@Data
public class RoleRequestDTO {
    private String name;
    private Set<Permission> permissions;
}

