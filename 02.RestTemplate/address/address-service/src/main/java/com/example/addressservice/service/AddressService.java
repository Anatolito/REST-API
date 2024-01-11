package com.example.addressservice.service;

import com.example.addressservice.entity.Address;
import com.example.addressservice.repository.AddressRepo;
import com.example.addressservice.response.AddressResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepo addressRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public AddressService(AddressRepo addressRepo, ModelMapper modelMapper) {
        this.addressRepo = addressRepo;
        this.modelMapper = modelMapper;
    }

    public AddressResponse findAddressByEmployeeId(int employeeId){
        Optional<Address> addressByEmployeeId = addressRepo.findAddressByEmployeeId(employeeId);
        AddressResponse addressResponse = modelMapper.map(addressByEmployeeId, AddressResponse.class);
        return addressResponse;
    }
}
