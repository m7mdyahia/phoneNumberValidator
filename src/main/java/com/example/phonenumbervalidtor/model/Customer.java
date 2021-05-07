package com.example.phonenumbervalidtor.model;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Customer {
    public long id;
    @NotNull
    private String name;
    @NotNull
    private PhoneNumber phone;
}
