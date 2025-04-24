package edu.miu.cs.cs489.appointmentservice.controller;

import edu.miu.cs.cs489.appointmentservice.dto.response.AddressResponseDTO;
import edu.miu.cs.cs489.appointmentservice.service.AddressService;
import edu.miu.cs.cs489.appointmentservice.service.serviceImpl.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private AddressServiceImpl addressService;

    @Autowired
    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public ResponseEntity<List<AddressResponseDTO>> listAddress() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

}
