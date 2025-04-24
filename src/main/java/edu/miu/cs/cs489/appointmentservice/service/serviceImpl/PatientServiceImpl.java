package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;

import edu.miu.cs.cs489.appointmentservice.dto.request.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.PatientResponseDTO;
import edu.miu.cs.cs489.appointmentservice.exception.ResourceNotFoundException;
import edu.miu.cs.cs489.appointmentservice.model.Patient;
import edu.miu.cs.cs489.appointmentservice.repository.PatientRepository;
import edu.miu.cs.cs489.appointmentservice.service.PatientService;
import edu.miu.cs.cs489.appointmentservice.util.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.Sort;

@Service
public class PatientServiceImpl implements PatientService {

        private final PatientRepository patientRepository;
        private final PatientMapper patientMapper;

        @Autowired
        public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
            this.patientRepository = patientRepository;
            this.patientMapper = patientMapper;
        }

        public List<PatientResponseDTO> getAllPatientsSortedByLastName() {
            return patientRepository.findAll(Sort.by("lastName").ascending())
                    .stream()
                    .map(patientMapper::toDTO)
                    .toList();
        }

        public PatientResponseDTO getPatientById(String id) {
            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
            return patientMapper.toDTO(patient);
        }

        public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
            Patient saved = patientRepository.save(patientMapper.toEntity(patientRequestDTO));
            saved.setPatientId(patientRequestDTO.patientId());
            return patientMapper.toDTO(saved);
        }

        public PatientResponseDTO updatePatient(String id, PatientRequestDTO patientRequestDTO) {
            Patient existing = patientRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

            Patient updated = patientMapper.toEntity(patientRequestDTO);
            updated.setPatientId(existing.getPatientId());

            return patientMapper.toDTO(patientRepository.save(updated));
        }

        public void deletePatient(String id) {
            if (!patientRepository.existsById(id)) {
                throw new ResourceNotFoundException("Patient not found with id: " + id);
            }
            patientRepository.deleteById(id);
        }

        public List<PatientResponseDTO> searchPatients(String searchString) {
            return patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                            searchString, searchString).stream()
                    .map(patientMapper::toDTO)
                    .toList();
        }
    }



