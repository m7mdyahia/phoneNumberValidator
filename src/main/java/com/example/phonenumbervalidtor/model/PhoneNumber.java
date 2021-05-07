package com.example.phonenumbervalidtor.model;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

@Data
@Builder
public class PhoneNumber {
    @NotNull
    private final String number ;
    @Nullable
    private final Country country ;
    private final boolean valid ;
}
