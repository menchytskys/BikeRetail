package com.epam.bikeRetail.oldDao.impl;

import com.epam.bikeRetail.entityes.Entity;
import com.epam.bikeRetail.entityes.bike.Bike;
import com.epam.bikeRetail.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BikeDAO extends AbstractDAO {

    private static final String SQL_SELECT_BIKES_BY_STATION_ID = "SELECT * FROM bikestation WHERE station_id=?";
    private static final String SQL_SELECT_BIKE_BY_BIKE_ID = "SELECT * FROM bike WHERE id=?";
    private static final String SQL_INSERT_BIKE = "INSERT INTO bike (priceOnHour, brandName, model) VALUES (?,?,?)";
    private static final String SQL_INSERT_BIKESTATION_STATION_ID =
            "INSERT INTO bikestation (bike_id, station_id) VALUES (?,?)";
    private static final String SQL_SELECT_BIKE_ID = "SELECT MAX(id) FROM bike ";
    private static final String SQL_DELETE_BIKE_ID_FROM_BIKE = "DELETE FROM bike WHERE id=?";
    private static final String SQL_DELETE_BIKE_ID_FROM_BIKESTATION = "DELETE FROM bikestation WHERE bike_id=?";
    private static final String SQL_UPDATE_STATION_ID_FROM_BIKESTATION =
            "UPDATE bikestation SET station_id=? WHERE bike_id=?";
    private static final String BIKE_ID = "bike_id";

    public BikeDAO(Connection connection) {
        super(connection);
    }

    public List<Bike> findBikesById(String id) throws DAOException {
        Bike bike = null;
        List<Bike> bikes = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BIKES_BY_STATION_ID);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String bikeId = resultSet.getString(BIKE_ID);

                PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_SELECT_BIKE_BY_BIKE_ID);
                preparedStatement1.setString(1, bikeId);

                ResultSet resultSet1 = preparedStatement1.executeQuery();

                while (resultSet1.next()) {
                    bike = createBike(resultSet1);
                    bikes.add(bike);
                }
            }
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
        return bikes;
    }

    public boolean addBike(Bike bike) throws DAOException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_BIKE);
            double priceOnHour = bike.getPriceOnHour();
            String bikePriceOnHour = Double.toString(priceOnHour);
            preparedStatement.setString(1, bikePriceOnHour);
            String brand = bike.getBrand();
            preparedStatement.setString(2, brand);
            String model = bike.getModel();
            preparedStatement.setString(3, model);

            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e){
            throw new DAOException("SQL exception detected.", e);
        }
    }

    public void addBikeToBikeStation(String stationId) throws DAOException {

        try {
            PreparedStatement preparedStatementSecond = connection.prepareStatement(SQL_SELECT_BIKE_ID);
            ResultSet resultSet = preparedStatementSecond.executeQuery();
            resultSet.next();
            String bikeId = resultSet.getString(1);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_BIKESTATION_STATION_ID);
            preparedStatement.setString(1, bikeId);
            preparedStatement.setString(2, stationId);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new DAOException("SQL exception detected.", e);
        }
    }

    public void updateBikeStation(String bikeId, String toStationId) throws DAOException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATION_ID_FROM_BIKESTATION);
            preparedStatement.setString(1,toStationId);
            preparedStatement.setString(2,bikeId);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("SQL exception detected.", e);
        }
    }

    @Override
    public List findAll() throws DAOException {
        return null;
    }

    @Override
    public Bike findEntityById(String bikeId) throws DAOException {
        Bike bike = null;

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_SELECT_BIKE_BY_BIKE_ID);
            preparedStatement1.setString(1, bikeId);

            ResultSet resultSet1 = preparedStatement1.executeQuery();

            resultSet1.next();
            bike = createBike(resultSet1);

            return bike;
        } catch (SQLException e){
            throw new DAOException("SQL exception detected.", e);
        }
    }

    @Override
    public void deleteById(String bikeId) throws DAOException {
        try {
            PreparedStatement preparedStatementSecond = connection.prepareStatement(SQL_DELETE_BIKE_ID_FROM_BIKESTATION);
            preparedStatementSecond.setString(1, bikeId);

            preparedStatementSecond.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BIKE_ID_FROM_BIKE);
            preparedStatement.setString(1,bikeId);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("SQL exception detected.", e);
        }
    }

    @Override
    public Entity update(Entity entity) {
        return null;
    }

    private Bike createBike(ResultSet resultSet) throws DAOException{
        try {
            Bike bike = new Bike();
            int id = resultSet.getInt("id");
            bike.setId(id);
            String brand = resultSet.getString("brandName");
            bike.setBrand(brand);
            String model = resultSet.getString("model");
            bike.setModel(model);
            double priceOnHour = resultSet.getDouble("priceOnHour");
            bike.setPriceOnHour(priceOnHour);

            return bike;
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }
}
