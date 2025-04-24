package edu.miu.cs.cs489.appointmentservice.service.serviceImpl;

import edu.miu.cs.cs489.appointmentservice.dto.response.AddressResponseDTO;
import edu.miu.cs.cs489.appointmentservice.dto.response.PatientResponseDTO2;
import edu.miu.cs.cs489.appointmentservice.model.Address;
import edu.miu.cs.cs489.appointmentservice.repository.AddressRepository;
import edu.miu.cs.cs489.appointmentservice.service.AddressService;
import edu.miu.cs.cs489.appointmentservice.util.PatientMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class AddressServiceImpl implements AddressService {

    private PatientMapper patientMapper;
    private AddressRepository addressRepository;

    @Autowired
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
