package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.dao.entity.CountryEntity;
import com.example.phonenumbervalidtor.dao.repository.CountryRepository;
import com.example.phonenumbervalidtor.model.Country;
import com.example.phonenumbervalidtor.model.PhoneNumber;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberService {

    private static List<Country> COUNTRIES = new ArrayList<>();

    @Autowired
    CountryRepository countryDAO;
    @Autowired
    CountryMapper countryMapper;

    @PostConstruct
    private void initializeCountries() {
        ArrayList<CountryEntity> countryEntities = new ArrayList<>();
        countryDAO.findAll().forEach(countryEntities::add);
        COUNTRIES = countryMapper.countryEntitiesToCountries(countryEntities);
    }


    public PhoneNumber getPhoneNumberFromPhoneString(@NotNull String phoneString) {
        Optional<Country> countryOptional = COUNTRIES
                .stream()
                .filter(country -> country.getPhonePattern().matcher(phoneString).matches())
                .findAny();
        return PhoneNumber
                .builder()
                .number(phoneString)
                .valid(countryOptional.isPresent())
                .country(countryOptional.orElse(null))
                .build();

    }

}
