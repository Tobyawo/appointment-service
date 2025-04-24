package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;


import edu.miu.cs.cs489.appointmentservice.dto.request.RoleRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.RoleResponseDTO;
import edu.miu.cs.cs489.appointmentservice.model.Role;
import edu.miu.cs.cs489.appointmentservice.repository.RoleRepository;
import edu.miu.cs.cs489.appointmentservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private RoleResponseDTO mapToDTO(Role role) {
        return new RoleResponseDTO(role.getId(), role.getName(), role.getPermissions());
    }



    @Override
    public Page<RoleResponseDTO> getAllRoles(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Role> roles = roleRepository.findAll(pageRequest);
        List<RoleResponseDTO> dtoList = roles.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageRequest, roles.getTotalElements());
    }

    @Override
    public Optional<RoleResponseDTO> getRoleById(Long id) {
        return roleRepository.findById(id).map(this::mapToDTO);
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setPermissions(request.getPermissions());
        Role saved = roleRepository.save(role);
        return mapToDTO(saved);
    }

    @Override
    public Optional<RoleResponseDTO> updateRole(Long id, RoleRequestDTO request) {
        return roleRepository.findById(id).map(role -> {
            role.setName(request.getName());
            role.setPermissions(request.getPermissions());
            return mapToDTO(roleRepository.save(role));
        });
    }

    @Override
    public boolean deleteRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
