package edu.miu.cs.cs489.appointmentservice.dto.response;


import edu.miu.cs.cs489.appointmentservice.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoleResponseDTO {
    private Long id;
    private String name;
    private Set<Permission> permissions;
}

