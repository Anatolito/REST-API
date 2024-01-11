package com.example.employeeservice.service;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.response.AddressResponse;
import com.example.employeeservice.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }


    public EmployeeResponse getEmployeeById(int id){
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = modelMapper.map(employee,EmployeeResponse.class);
        AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8081/address-service/address/{id}", AddressResponse.class,id);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}
