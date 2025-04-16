package edu.miu.cs.cs489.appointmentservice.service;


import edu.miu.cs.cs489.appointmentservice.dto.AddressResponseDTO;
import edu.miu.cs.cs489.appointmentservice.model.Address;

import java.util.List;

public interface AddressService {

    Address addNewAddress(Address newAddress);
    List<AddressResponseDTO> getAllAddresses();

    void deleteAddressById(Long addressId);

}
