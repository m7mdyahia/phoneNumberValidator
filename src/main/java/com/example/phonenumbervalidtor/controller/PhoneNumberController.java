package com.example.phonenumbervalidtor.controller;

import com.example.phonenumbervalidtor.model.Customer;
import com.example.phonenumbervalidtor.model.CustomerSearchCriteria;
import com.example.phonenumbervalidtor.model.PhoneNumber;
import com.example.phonenumbervalidtor.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/phoneNumber")
public class PhoneNumberController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<PhoneNumber> findAll() {
        return customerService.findAll().stream().map(Customer::getPhone).collect(Collectors.toList());
    }

    @PostMapping("/search")
    public List<PhoneNumber> findByCriteria(@RequestBody CustomerSearchCriteria customerSearchCriteria) {
        return customerService.findByCustomerSearchCriteria(customerSearchCriteria).stream().map(Customer::getPhone).collect(Collectors.toList());
    }

}
