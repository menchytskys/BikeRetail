package com.epam.bikeRetail.oldDao.impl;

import com.epam.bikeRetail.entityes.Entity;
import com.epam.bikeRetail.entityes.station.Station;
import com.epam.bikeRetail.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDAO extends AbstractDAO{

    private static final String SQL_SELECT_ALL_STATIONS = "SELECT * FROM station";
    private static final String SQL_SELECT_STATION_BY_ID = "SELECT * FROM station WHERE id=?";

    public StationDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Station> findAll() throws DAOException {
        List<Station> stations = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_STATIONS);
            while (resultSet.next()){
                Station station = new Station();
                station.setId(resultSet.getInt("id"));
                station.setNameStation((resultSet.getString("nameStation")));
                stations.add(station);
            }
        }catch (SQLException e){
           throw new DAOException("SQL exception detected.", e);
        }finally {
            close(statement);
        }

        return stations;
    }

    @Override
    public Station findEntityById(String id) throws DAOException{

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_STATION_BY_ID);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return createStation(resultSet);
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Entity update(Entity entity) {
        return null;
    }

    public Station createStation(ResultSet resultSet) throws DAOException{

        try {
            Station station = null;

            if(resultSet.next()) {
                station = new Station();

                int id = resultSet.getInt("id");
                station.setId(id);

                String nameStation = resultSet.getString("nameStation");
                station.setNameStation(nameStation);
            }

            return station;
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }
}
