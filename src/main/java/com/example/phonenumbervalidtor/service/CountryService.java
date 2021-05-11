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
    private List<Country> countryList = new ArrayList<>();
    @NotNull
    private Map<Integer, Country> countryCodeMap = new HashMap<>();
    @NotNull
    private Map<Integer, Country> countryIdMap = new HashMap<>();

    @Autowired
    CountryRepository countryRepo;
    @Autowired
    CountryMapper countryMapper;

    @PostConstruct
    public void initializeCountries() {
        countryList = getCountriesFromRepo();
        countryCodeMap = getCountryCodeMapFromCountryList(countryList);
        countryIdMap = getCountryIdMap(countryList);
    }

    public List<Country> getCountriesFromRepo() {
        ArrayList<CountryEntity> countryEntities = new ArrayList<>();
        countryRepo.findAll().forEach(countryEntities::add);
        return countryMapper.countryEntitiesToCountries(countryEntities);
    }

    @Nullable
    public Country getCountryByCode(int code) {
        return countryCodeMap.get(code);
    }

    @Nullable
    public Country getCountryById(int id) {
        return countryIdMap.get(id);
    }

    public @NotNull List<Country> getCountryList() {
        return countryList;
    }

    @NotNull
    public Map<Integer, Country> getCountryCodeMapFromCountryList(@NotNull List<Country> countryList) {
        return countryList
                .stream()
                .collect(Collectors.toMap(Country::getCode, Function.identity()));
    }

    @NotNull
    public Map<Integer, Country> getCountryIdMap(@NotNull List<Country> countryList) {
        return countryList
                .stream()
                .collect(Collectors.toMap(Country::getId, Function.identity()));
    }
}
