package com.example.phonenumbervalidtor.dao.repository;

import com.example.phonenumbervalidtor.dao.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {
}
