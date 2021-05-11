package com.example.phonenumbervalidtor.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    void testConstructor()
    {
        Country cameroon = new Country(1, 237, "Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$");

        assertTrue(cameroon.getPhonePattern().matcher("(237) 697151594").matches());

    }
    @Test
    void testInvalidConstructor()
    {
        assertThrows(Throwable.class,()->new Country(1, 237, "Cameroon", null));

    }

}