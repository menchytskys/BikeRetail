package com.epam.bikeRetail.entity;

import com.epam.bikeRetail.dao.Identifiable;

import java.math.BigDecimal;

public class Bike implements Identifiable {

    private int id;
    private String model;
    private String brand;
    private BigDecimal priceOnHour;

    public Bike() {
    }

    public Bike(int id, String model, String brand, BigDecimal priceOnHour) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.priceOnHour = priceOnHour;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPriceOnHour() {
        return priceOnHour;
    }

    public void setPriceOnHour(BigDecimal priceOnHour) {
        this.priceOnHour = priceOnHour;
    }
}


