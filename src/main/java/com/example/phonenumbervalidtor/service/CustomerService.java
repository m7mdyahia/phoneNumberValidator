package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.dao.entity.CustomerEntity;
import com.example.phonenumbervalidtor.dao.repository.CustomerRepository;
import com.example.phonenumbervalidtor.model.Country;
import com.example.phonenumbervalidtor.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PhoneNumberService phoneNumberService;

    public List<Customer> findAll() {
        return getCustomersListFromCustomerEntities(customerRepository.findAll());
    }

    public List<Customer> findByCountry(Country country) {
        return getCustomersListFromCustomerEntities(customerRepository.findByPhoneStartsWith("(" + country.getCode() + ")"));
    }

    private List<Customer> getCustomersListFromCustomerEntities(Iterable<CustomerEntity> customerEntities) {
        List<Customer> customers = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            customers.add(new Customer(customerEntity.getName(),
                    phoneNumberService.getPhoneNumberFromPhoneString(customerEntity.getPhone())));
        }
        return customers;
    }
}
