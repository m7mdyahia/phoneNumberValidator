package com.example.phonenumbervalidtor.controller;

import com.example.phonenumbervalidtor.model.Country;
import com.example.phonenumbervalidtor.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountryController {

    @Autowired
    CountryService countryService;


    @GetMapping
    public List<Country> findAllCountries() {
        return countryService.getCountryList();
    }

}
