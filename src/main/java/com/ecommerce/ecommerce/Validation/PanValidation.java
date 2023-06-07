package com.ecommerce.ecommerce.Validation;

import java.util.regex.Pattern;

public class PanValidation {
    private static final String PAN_REGEX = "^([A-Za-z]{3})\\d{4}([A-Za-z]{1})$";

    public static boolean validate(String panNumber) {
        return Pattern.matches(PAN_REGEX, panNumber);
    }
}
