package com.example.employeeservice.service;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.feignclient.AddressClient;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.response.AddressResponse;
import com.example.employeeservice.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    private final ModelMapper modelMapper;

    private final AddressClient addressClient;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper, AddressClient addressClient) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
        this.addressClient = addressClient;
    }

    public EmployeeResponse getEmployeeById(int id){
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = modelMapper.map(employee,EmployeeResponse.class);
        ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(id);
        employeeResponse.setAddressResponse(addressResponse.getBody());
        return employeeResponse;
    }
}
