package com.ecommerce.ecommerce.Validation;

import java.util.regex.Pattern;

public class CitizenshipValidation {
    private static final String CITIZENSHIP_REGEX = "^[0-9]{13}$";

    public static boolean validate(String citizenshipNumber) {
        return Pattern.matches(CITIZENSHIP_REGEX, citizenshipNumber);
    }
}
