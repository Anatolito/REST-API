package com.example.addressservice.controller;

import com.example.addressservice.response.AddressResponse;
import com.example.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId){
        AddressResponse addressResponse = addressService.findAddressByEmployeeId(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }
}
