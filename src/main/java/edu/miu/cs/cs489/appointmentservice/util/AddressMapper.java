package edu.miu.cs.cs489.appointmentservice.util;

import edu.miu.cs.cs489.appointmentservice.dto.response.AddressResponseDTO;
import edu.miu.cs.cs489.appointmentservice.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",  uses = { PatientMapper.class })
public interface AddressMapper {
    AddressResponseDTO toDTO(Address address);
//    Address toEntity(AddressResponseDTO addressDto);

//    List<AddressResponseDTO> toDTOs(List<Address> addresses);

}
