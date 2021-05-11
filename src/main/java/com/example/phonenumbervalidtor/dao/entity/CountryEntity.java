package com.example.phonenumbervalidtor.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "country")
public class CountryEntity {
    @Id
    Integer id;
    Integer code;
    @NotNull
    String name;
    @NotNull
    String phonePattern;
}
