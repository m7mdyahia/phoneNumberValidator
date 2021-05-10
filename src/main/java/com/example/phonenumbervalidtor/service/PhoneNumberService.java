package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.model.Country;
import com.example.phonenumbervalidtor.model.PhoneNumber;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneNumberService {

    @Autowired
    CountryService countryService;
    Pattern PHONE_NUMBER_PATTERN = Pattern.compile("\\((\\d+)\\)\\s?(.+)");

    public PhoneNumber getPhoneNumberFromPhoneString(@NotNull String phoneString) {
        PhoneNumber.PhoneNumberBuilder builder = PhoneNumber
                .builder()
                .fullNumber(phoneString);
        int countryCode;
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneString);
        if (matcher.matches()) {
            countryCode = Integer.parseInt(matcher.group(1));
            Country country = countryService.getCountryByCode(countryCode);
            builder.country(country);
            builder.number(matcher.group(2));
            if (country != null) {
                builder.valid(isPhoneNumberValidForCountry(phoneString, country));
            } else {
                builder.valid(false);
            }
        } else {
            builder.valid(false);
        }
        return builder.build();
    }

    public static boolean isPhoneNumberValidForCountry(@NotNull String phoneNumber,
                                                @NotNull Country country) {
        return country.getPhonePattern().matcher(phoneNumber).matches();
    }


}
