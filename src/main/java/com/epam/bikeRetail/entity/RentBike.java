package com.epam.bikeRetail.entity;

import com.epam.bikeRetail.dao.Identifiable;

/**
 * This class describes rentBike of application.
 *
 * @author Stepan Menchytskiy
 * @see UserRole
 * @see Identifiable
 */
public class RentBike implements Identifiable{
    private int rentTime;
    private int userId;
    private int bikeId;

    public RentBike() {
    }

    public RentBike(int rentTime, int userId, int bikeId) {
        this.rentTime = rentTime;
        this.userId = userId;
        this.bikeId = bikeId;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int key) {

    }
}
