package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;

import edu.miu.cs.cs489.appointmentservice.dto.AddressResponseDTO;
import edu.miu.cs.cs489.appointmentservice.dto.PatientResponseDTO2;
import edu.miu.cs.cs489.appointmentservice.model.Address;
import edu.miu.cs.cs489.appointmentservice.repository.AddressRepository;
import edu.miu.cs.cs489.appointmentservice.service.AddressService;
import edu.miu.cs.cs489.appointmentservice.util.PatientMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final PatientMapper patientMapper;
    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository, PatientMapper patientMapper) {
        this.addressRepository = addressRepository;
        this.patientMapper = patientMapper;
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
                        (a.getPatient()!= null)?new PatientResponseDTO2(
                                a.getPatient().getPatientId(),
                                a.getPatient().getFirstName(),
                                a.getPatient().getLastName(),
                                a.getPatient().getPhone()
                        ): null
                )).toList();
    }

    @Override
    public void deleteAddressById(Long addressId) {
        addressRepository.deleteById(addressId);
    }


}
