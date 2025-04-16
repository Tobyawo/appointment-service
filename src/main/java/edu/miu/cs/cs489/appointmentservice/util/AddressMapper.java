package edu.miu.cs.cs489.appointmentservice.util;

import edu.miu.cs.cs489.appointmentservice.dto.AddressResponseDTO;
import edu.miu.cs.cs489.appointmentservice.dto.PatientRequestDTO;
import edu.miu.cs.cs489.appointmentservice.dto.PatientResponseDTO;
import edu.miu.cs.cs489.appointmentservice.model.Address;
import edu.miu.cs.cs489.appointmentservice.model.Patient;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",  uses = { PatientMapper.class })
public interface AddressMapper {
//    @Mapping(source = "patient", target = "patient")
    AddressResponseDTO toDTO(Address address);
    Address toEntity(AddressResponseDTO addressDto);

    List<AddressResponseDTO> toDTOs(List<Address> addresses);

}
