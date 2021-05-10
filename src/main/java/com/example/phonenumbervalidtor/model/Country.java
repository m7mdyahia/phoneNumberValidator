package com.example.phonenumbervalidtor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

@Value
public class Country {
    int id;
    int code;
    @EqualsAndHashCode.Exclude
    @NotNull
    String name;
    @NotNull
    @JsonIgnore
    Pattern phonePattern;

    public Country(int id,int code, @NotNull String name, @NotNull String phonePattern) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phonePattern = Pattern.compile(phonePattern);
    }
}
