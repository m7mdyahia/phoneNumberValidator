package com.example.phonenumbervalidtor.controller;

import com.example.phonenumbervalidtor.model.Customer;
import com.example.phonenumbervalidtor.model.CustomerSearchCriteria;
import com.example.phonenumbervalidtor.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/search")
    public List<Customer> findByCriteria(@RequestBody CustomerSearchCriteria customerSearchCriteria) {
        return customerService.findByCustomerSearchCriteria(customerSearchCriteria);
    }

}
