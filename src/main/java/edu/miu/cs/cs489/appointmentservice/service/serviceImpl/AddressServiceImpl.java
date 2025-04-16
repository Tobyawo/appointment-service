package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;

import edu.miu.cs.cs489.appointmentservice.dto.AddressResponseDTO;
import edu.miu.cs.cs489.appointmentservice.dto.PatientResponseDTO;
import edu.miu.cs.cs489.appointmentservice.model.Address;
import edu.miu.cs.cs489.appointmentservice.repository.AddressRepository;
import edu.miu.cs.cs489.appointmentservice.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addNewAddress(Address newAddress) {
        return addressRepository.save(newAddress);
    }

    @Override
    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(a -> new AddressResponseDTO(
                        a.getId(),
                        a.getStreet(),
                        a.getCity(),
                        a.getState(),
                        a.getZip(),
                        (a.getPatient()!= null)?new PatientResponseDTO(
                                a.getPatient().getPatientId(),
                                a.getPatient().getFirstName(),
                                a.getPatient().getLastName(),
                                a.getPatient().getPhone(),
                                null


                        ): null
                )).toList();
    }

    @Override
    public void deleteAddressById(Long addressId) {
        addressRepository.deleteById(addressId);
    }


}
