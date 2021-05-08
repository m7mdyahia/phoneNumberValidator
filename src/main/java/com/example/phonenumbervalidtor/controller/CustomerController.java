package com.example.phonenumbervalidtor.controller;

import com.example.phonenumbervalidtor.model.Country;
import com.example.phonenumbervalidtor.model.Customer;
import com.example.phonenumbervalidtor.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }
    @GetMapping("/search")
    public List<Customer> findByCountry(@RequestParam Country country) {
        return customerService.findByCountry(country);
    }

}
