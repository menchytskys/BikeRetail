package com.epam.bikeRetail.utils;

import
        org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BikeDataValidatorTest {

    private static BikeDataValidator bikeDataValidator;

    @BeforeClass
    public static void setUserDataValidator(){
        bikeDataValidator = new BikeDataValidator();
    }

    @Test
    public void ShouldReturnTrueWhenValidate() {
        //given
        String brand = "GT";
        String model = "agro";
        String priceOnHour = "1.2";
        //when
        boolean actual = bikeDataValidator.checkData(brand, model, priceOnHour);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void ShouldReturnFalseWhenValidate() {
        //given
        String brand = "GT_?";
        String model = "agro";
        String priceOnHour = "1.2 asd";
        //when
        boolean actual = bikeDataValidator.checkData(brand, model, priceOnHour);
        //then
        Assert.assertFalse(actual);
    }
}
