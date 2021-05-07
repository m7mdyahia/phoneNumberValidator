package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.dao.entity.CountryEntity;
import com.example.phonenumbervalidtor.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country countryEntityToCountry(CountryEntity countryEntity);
    List<Country> countryEntitiesToCountries(List<CountryEntity> countryEntity);
}
