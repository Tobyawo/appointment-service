package edu.miu.cs.cs489.appointmentservice.util;

import edu.miu.cs.cs489.appointmentservice.dto.request.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.PatientResponseDTO;
import edu.miu.cs.cs489.appointmentservice.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface PatientMapper {
    PatientResponseDTO toDTO(Patient patient);
    Patient toEntity(PatientRequestDTO patientRequestDTO);
}

