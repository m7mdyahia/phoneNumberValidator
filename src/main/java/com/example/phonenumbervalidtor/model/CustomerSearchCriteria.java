package com.example.phonenumbervalidtor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchCriteria {
    @NotNull
    List<Integer> selectedCountries;
    @NotNull
    List<Boolean> selectedStatuses;
}
