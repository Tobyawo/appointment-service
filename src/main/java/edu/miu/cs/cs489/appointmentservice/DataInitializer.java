package edu.miu.cs.cs489.appointmentservice;

import edu.miu.cs.cs489.appointmentservice.model.*;
import edu.miu.cs.cs489.appointmentservice.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final AppointmentRepository appointmentRepository;
    private final DentistRepository dentistRepository;
    private final PatientRepository patientRepository;
    private final SurgeryRepository surgeryRepository;

    public DataInitializer(PermissionRepository permissionRepository,
                           RoleRepository roleRepository,
                           AppointmentRepository appointmentRepository,
                           DentistRepository dentistRepository,
                           PatientRepository patientRepository,
                           SurgeryRepository surgeryRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.appointmentRepository = appointmentRepository;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
        this.surgeryRepository = surgeryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Initialize permissions and roles
        if (permissionRepository.count() == 0 && roleRepository.count() == 0) {
            Permission adminRead = new Permission("ADMIN:READ");
            Permission adminWrite = new Permission("ADMIN:WRITE");
            Permission memberRead = new Permission("member:read");
            Permission memberWrite = new Permission("member:write");

            permissionRepository.saveAll(List.of(adminRead, adminWrite, memberRead, memberWrite));

            Role admin = new Role("ADMIN");
            admin.setPermissions(Set.of(adminRead, adminWrite));

            Role member = new Role("MEMBER");
            member.setPermissions(Set.of(memberRead, memberWrite));

            roleRepository.saveAll(List.of(admin, member));
            System.out.println("Initial roles and permissions loaded.");
        }

        // Initialize sample appointment data
        if (appointmentRepository.count() == 0) {
            Dentist dentist1 = new Dentist("Tony","Smith");
            Dentist dentist2 = new Dentist("Helen", "Pearson");
            Dentist dentist3 = new Dentist("Robin", "Plevin");

            dentistRepository.saveAll(List.of(dentist1, dentist2, dentist3));

            Address  address1 = new Address("1000N 4th Street", "Fairfield", "IA", "52557");
            Address  address2 = new Address("1001N 4th Street", "Fairfield", "IA", "52557");
            Patient patient1 = new Patient("P100", "Gillian" , "White", "222-321-323", "gillian@gmail.com", LocalDate.of(1993,8,4), true, address1);
            Patient patient2 = new Patient("P105", "Jill", "Bell" , "112-321-323", "bell@gmail.com", LocalDate.of(1989,2,5), false, address2);
            Patient patient3 = new Patient("P108", "Ian", "MacKay", "451-321-003", "mackay@gmail.com", LocalDate.of(2001,12,7), false, address1);
            Patient patient4 = new Patient("P110", "John", "Walker", "401-781-954", "walker@gmail.com", LocalDate.of(2004,6,1), false, address2);

            patientRepository.saveAll(List.of(patient1, patient2, patient3, patient4));

            Surgery surgery1 = new Surgery("S15");
            Surgery surgery2 = new Surgery("S10");
            Surgery surgery3 = new Surgery("S13");

            surgeryRepository.saveAll(List.of(surgery1, surgery2, surgery3));

            List<Appointment> appointments = List.of(
                    new Appointment(LocalDate.of(2013, 9, 12), LocalTime.of(10, 0), "Scheduled", dentist1, patient1, surgery1),
                    new Appointment(LocalDate.of(2013, 9, 12), LocalTime.of(12, 0), "Scheduled", dentist1, patient2, surgery1),
                    new Appointment(LocalDate.of(2013, 9, 12), LocalTime.of(10, 0), "Scheduled", dentist2, patient3, surgery2),
                    new Appointment(LocalDate.of(2013, 9, 14), LocalTime.of(14, 0), "Scheduled", dentist2, patient3, surgery2),
                    new Appointment(LocalDate.of(2013, 9, 14), LocalTime.of(16, 30), "Scheduled", dentist3, patient2, surgery1),
                    new Appointment(LocalDate.of(2013, 9, 15), LocalTime.of(18, 0), "Scheduled", dentist3, patient4, surgery3)
            );

            appointmentRepository.saveAll(appointments);
            System.out.println("Sample appointments loaded.");
        }
    }
}
