package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.dao.entity.CountryEntity;
import com.example.phonenumbervalidtor.dao.repository.CountryRepository;
import com.example.phonenumbervalidtor.model.Country;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CountryService {
    @NotNull
    private  List<Country> countryList = new ArrayList<>();
    @NotNull
    private  Map<Integer, Country> countryCodeMap = new HashMap<>();
    private  Map<Integer, Country> countryIdMap = new HashMap<>();

    @Autowired
    CountryRepository countryDAO;

    @Autowired
    CountryMapper countryMapper;

    @PostConstruct
    private void initializeCountries() {
        ArrayList<CountryEntity> countryEntities = new ArrayList<>();
        countryDAO.findAll().forEach(countryEntities::add);
        countryList = countryMapper.countryEntitiesToCountries(countryEntities);

        countryCodeMap = countryList
                .stream()
                .collect(Collectors.toMap(Country::getCode, Function.identity()));
        countryIdMap = countryList
                .stream()
                .collect(Collectors.toMap(Country::getId, Function.identity()));
    }

    @Nullable
    public Country getCountryByCode(int code)
    {
        return countryCodeMap.get(code);
    }
    @Nullable
    public Country getCountryById(int code)
    {
        return countryIdMap.get(code);
    }

    public List<Country> getCountryList() {
        return countryList;
    }
}
