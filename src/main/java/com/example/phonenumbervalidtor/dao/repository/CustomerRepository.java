package com.example.phonenumbervalidtor.dao.repository;

import com.example.phonenumbervalidtor.dao.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    List<CustomerEntity> findByName(String name);
    List<CustomerEntity> findByPhoneStartsWith(String prefix);
}
