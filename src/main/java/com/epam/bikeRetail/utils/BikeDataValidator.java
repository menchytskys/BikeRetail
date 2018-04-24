package com.epam.bikeRetail.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BikeDataValidator {

    private static final String BRAND_AND_PATTERN = "[a-zA-Z0-9_]+{6,}";
    private static final String PRICE_IN_HOUR_PATTERN = "\\d+\\.\\d+";

    /**
     * Check user registration data for errors.
     *
     * @param brand     the user's login.
     * @param model  the user's password.
     * @param priceInHour the user's first name.
     * @return result of validation.
     */
    public boolean checkData(String brand, String model, String priceInHour){

        boolean isBrandValid = matchPattern(brand, BRAND_AND_PATTERN);
        boolean isModelValid = matchPattern(model, BRAND_AND_PATTERN);
        boolean isPriceInHourValid = matchPattern(priceInHour, PRICE_IN_HOUR_PATTERN);

        return isBrandValid && isModelValid && isPriceInHourValid ;
    }

    private boolean matchPattern(String data, String currentPattern) {
        Pattern pattern = Pattern.compile(currentPattern);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }

}
