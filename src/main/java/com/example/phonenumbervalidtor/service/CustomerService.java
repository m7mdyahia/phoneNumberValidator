package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.dao.entity.CustomerEntity;
import com.example.phonenumbervalidtor.dao.repository.CustomerRepository;
import com.example.phonenumbervalidtor.model.Country;
import com.example.phonenumbervalidtor.model.Customer;
import com.example.phonenumbervalidtor.model.CustomerSearchCriteria;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PhoneNumberService phoneNumberService;
    @Autowired
    CountryService countryService;

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

    public List<Customer> findByCustomerSearchCriteria(@NotNull CustomerSearchCriteria customerSearchCriteria) {

        return customerSearchCriteria
                .getSelectedCountries()
                .stream()
                .map(countryService::getCountryById)
                .filter(Objects::nonNull)
                .map(this::findByCountry)
                .flatMap(Collection::stream)
                .filter(customer -> customerSearchCriteria.getSelectedStatuses().stream().anyMatch(b -> b == customer.getPhone().isValid()))
                .collect(Collectors.toList());
    }
}
