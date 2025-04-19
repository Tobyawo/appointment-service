package edu.miu.cs.cs489.appointmentservice.service;


import edu.miu.cs.cs489.appointmentservice.dto.RoleRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.RoleResponseDTO;
import org.springframework.data.domain.Page;


import java.util.Optional;


public interface RoleService {
    Page<RoleResponseDTO> getAllRoles(int page, int size);
    Optional<RoleResponseDTO> getRoleById(Long id);
    RoleResponseDTO createRole(RoleRequestDTO request);
    Optional<RoleResponseDTO> updateRole(Long id, RoleRequestDTO request);
    boolean deleteRole(Long id);
}


