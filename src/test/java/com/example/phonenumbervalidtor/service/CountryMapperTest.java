package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.dao.entity.CountryEntity;
import com.example.phonenumbervalidtor.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CountryMapperTest {
    @Autowired
    CountryMapper countryMapper;
    private static final CountryEntity cameroon =
            CountryEntity.builder().id(1).code(237).name("Cameroon").phonePattern("\\(237\\)\\ ?[2368]\\d{7,8}$").build();
    private static final CountryEntity ethiopia =
            CountryEntity.builder().id(2).code(251).name("Ethiopia").phonePattern("\\(251\\)\\ ?[1-59]\\d{8}$").build();

    @Test
    void countryEntityToCountryTest() {
        Country country = countryMapper.countryEntityToCountry(cameroon);
        assertEquals(1, country.getId());
        assertEquals(237, country.getCode());
        assertEquals("Cameroon", country.getName());
        assertTrue(country.getPhonePattern().matcher("(237) 697151594").matches());
    }

    @Test
    void countryEntitiesToCountries() {
        List<Country> countries = countryMapper.countryEntitiesToCountries(Stream.of(cameroon, ethiopia).collect(Collectors.toList()));
        assertEquals(2,countries.size());
    }
}