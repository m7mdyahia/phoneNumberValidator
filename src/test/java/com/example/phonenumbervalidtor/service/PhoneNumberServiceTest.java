package com.example.phonenumbervalidtor.service;

import com.example.phonenumbervalidtor.model.Country;
import com.example.phonenumbervalidtor.model.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneNumberServiceTest {

    public static final Country CAMEROON = new Country(1, 237, "Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$");
    @Autowired
    PhoneNumberService phoneNumberService;

    @Test
    void testIsPhoneNumberValidForCountry() {
        assertTrue(PhoneNumberService.isPhoneNumberValidForCountry("(237) 697151594", CAMEROON));
    }
    @Test
    void testIsPhoneNumberValidForCountryInvalidCase() {
        assertFalse(PhoneNumberService.isPhoneNumberValidForCountry("(237) 6A0311634", CAMEROON));
    }

    @Test
    void testIsPhoneNumberValidForCountryOtherCountryCode() {
        assertFalse(PhoneNumberService.isPhoneNumberValidForCountry("(999) 697151594", CAMEROON));
    }

    @Test
    void testIsPhoneNumberValidForCountryNoCountryCode() {
        assertFalse(PhoneNumberService.isPhoneNumberValidForCountry("697151594", CAMEROON));
    }
    @Test
    void testGetPhoneNumberFromPhoneStringValidCase()
    {
        PhoneNumber phoneNumberFromPhoneString = phoneNumberService.getPhoneNumberFromPhoneString("(237) 697151594");
        assertEquals(CAMEROON, phoneNumberFromPhoneString.getCountry());
        assertTrue(phoneNumberFromPhoneString.isValid());
    }

    @Test
    void testGetPhoneNumberFromPhoneStringValidCountryCodeInvalidNumber()
    {
        PhoneNumber phoneNumberFromPhoneString = phoneNumberService.getPhoneNumberFromPhoneString("(237) 6971515X4");
        assertEquals(CAMEROON, phoneNumberFromPhoneString.getCountry());
        assertFalse(phoneNumberFromPhoneString.isValid());
    }
    @Test
    void testGetPhoneNumberFromPhoneStringInvalidCountryCodeValidNumber()
    {
        PhoneNumber phoneNumberFromPhoneString = phoneNumberService.getPhoneNumberFromPhoneString("(999) 697151594");
        assertNull(phoneNumberFromPhoneString.getCountry());
        assertFalse(phoneNumberFromPhoneString.isValid());
    }
    @Test
    void testGetPhoneNumberFromPhoneStringNoCountryCode()
    {
        PhoneNumber phoneNumberFromPhoneString = phoneNumberService.getPhoneNumberFromPhoneString("697151594");
        assertNull(phoneNumberFromPhoneString.getCountry());
        assertFalse(phoneNumberFromPhoneString.isValid());
    }
}