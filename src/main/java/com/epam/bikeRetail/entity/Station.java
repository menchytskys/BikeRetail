package com.epam.bikeRetail.entity;

import com.epam.bikeRetail.dao.Identifiable;

public class Station implements Identifiable{

    private int id;
    private String nameStation;

    public Station() {
    }

    public Station(int id, String nameStation) {
        this.id = id;
        this.nameStation = nameStation;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }
}
