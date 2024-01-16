package com.example.employeeservice.service;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.response.AddressResponse;
import com.example.employeeservice.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(@Value("${addressservice.base.url}") String addressBaseUrl, RestTemplateBuilder builder) {
        this.restTemplate = builder
                .rootUri(addressBaseUrl)
                .build();
    }


    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        AddressResponse addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}
