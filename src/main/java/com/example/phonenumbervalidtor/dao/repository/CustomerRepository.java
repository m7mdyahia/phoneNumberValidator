package com.example.phonenumbervalidtor.dao.repository;

import com.example.phonenumbervalidtor.dao.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

}
