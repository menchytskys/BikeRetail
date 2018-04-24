package com.epam.bikeRetail.entity;

import com.epam.bikeRetail.dao.Identifiable;

public class BikeStation implements Identifiable {

    private int id;
    private int bikeId;
    private int stationId;

    public BikeStation() {
    }

    public BikeStation(int bikeId, int stationId) {
        this.bikeId = bikeId;
        this.stationId = stationId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int key) {
        this.id = key;
    }
}
