package com.example.phonenumbervalidtor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CountryServiceTest {
    @Autowired
    CountryService countryService;

    @Test
    void countriesLoadedFromDatabase()
    {
        assertThat(countryService.getCountryList()).isNotEmpty();
    }

    @Test
    void getCountryByCode() {
        assertThat(countryService.getCountryById(5).getName()).isEqualTo("Uganda");
    }

    @Test
    void getCountryByCodeForUnkownCountry() {
        assertThat(countryService.getCountryById(115)).isNull();
    }

    @Test
    void getCountryById() {
        assertThat(countryService.getCountryByCode(258).getName()).isEqualTo("Mozambique");
    }

    @Test
    void getCountryByIdForUnkownCountry() {
        assertThat(countryService.getCountryByCode(9999)).isNull();
    }
}