package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;

import edu.miu.cs.cs489.appointmentservice.dto.request.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.PatientResponseDTO;
import edu.miu.cs.cs489.appointmentservice.exception.ResourceNotFoundException;
import edu.miu.cs.cs489.appointmentservice.model.Patient;
import edu.miu.cs.cs489.appointmentservice.repository.PatientRepository;
import edu.miu.cs.cs489.appointmentservice.util.PatientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    void testGetAllPatientsSortedByLastName() {
        List<Patient> patients = List.of(new Patient("1", "John", "Doe"));
        when(patientRepository.findAll(Sort.by("lastName").ascending())).thenReturn(patients);
        when(patientMapper.toDTO(any())).thenReturn(new PatientResponseDTO("1", "John", "Doe"));

        List<PatientResponseDTO> result = patientService.getAllPatientsSortedByLastName();

        assertEquals(1, result.size());
        verify(patientRepository).findAll(Sort.by("lastName").ascending());
    }

    @Test
    void testGetPatientById_PatientFound() {
        String id = "1";
        Patient patient = new Patient(id, "Jane", "Doe");
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(patientMapper.toDTO(patient)).thenReturn(new PatientResponseDTO(id, "Jane", "Doe", "223-444", null));

        PatientResponseDTO result = patientService.getPatientById(id);

        assertEquals("Jane", result.firstName());
    }

    @Test
    void testGetPatientById_NotFound() {
        when(patientRepository.findById("99")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById("99"));
    }

    @Test
    void testCreatePatient() {
        PatientRequestDTO request = new PatientRequestDTO("1", "Jane", "Doe");
        Patient patient = new Patient("1", "Jane", "Doe");

        when(patientMapper.toEntity(request)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toDTO(patient)).thenReturn(new PatientResponseDTO("1", "Jane", "Doe"));

        PatientResponseDTO result = patientService.createPatient(request);

        assertEquals("Jane", result.firstName());
    }

    @Test
    void testDeletePatient_NotFound() {
        when(patientRepository.existsById("abc")).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> patientService.deletePatient("abc"));
    }
}
