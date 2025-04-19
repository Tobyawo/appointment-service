package edu.miu.cs.cs489.appointmentservice;

import edu.miu.cs.cs489.appointmentservice.model.Permission;
import edu.miu.cs.cs489.appointmentservice.model.Role;
import edu.miu.cs.cs489.appointmentservice.repository.PermissionRepository;
import edu.miu.cs.cs489.appointmentservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (permissionRepository.count() == 0 && roleRepository.count() == 0) {
            // Create permissions
            Permission adminRead = new Permission("admin:read");
            Permission adminWrite = new Permission("admin:write");
            Permission memberRead = new Permission("member:read");
            Permission memberWrite = new Permission("member:write");

            permissionRepository.saveAll(List.of(adminRead, adminWrite, memberRead, memberWrite));

            // Create roles and assign permissions
            Role admin = new Role("ADMIN");
            admin.setPermissions(Set.of(adminRead, adminWrite));

            Role member = new Role("MEMBER");
            member.setPermissions(Set.of(memberRead, memberWrite));

            roleRepository.saveAll(List.of(admin, member));

            System.out.println("✅ Initial roles and permissions loaded.");
        } else {
            System.out.println("ℹ️ Roles and permissions already initialized.");
        }
    }
}
