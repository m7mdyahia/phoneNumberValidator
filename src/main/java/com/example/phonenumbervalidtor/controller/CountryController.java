package com.example.phonenumbervalidtor.controller;

import com.example.phonenumbervalidtor.dao.entity.CountryEntity;
import com.example.phonenumbervalidtor.dao.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/country")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public Iterable<CountryEntity> findAllCountries() {
        return countryRepository.findAll();
    }

}
