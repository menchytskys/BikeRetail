package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.entity.BikeStation;
import com.epam.bikeRetail.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeStationDAO extends AbstractDAO<BikeStation> {
    private static final String UPDATE_QUERY = "UPDATE bikestation SET station_id=? WHERE bike_id=?";
    private static final String CREATE_QUERY = "INSERT INTO bikestation (bike_id, station_id) VALUES (?,?)";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM bikestation WHERE station_id=?";
    private static final String SELECT_QUERY = "SELECT * FROM bikestation";
    private static final String DELETE_QUERY = "DELETE FROM bikestation WHERE bike_id=?";

    public BikeStationDAO(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getQueryById() {
        return SELECT_QUERY_BY_ID;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected BikeStation build(ResultSet resultSet) throws DAOException {
        try {
            BikeStation bikeStation = new BikeStation();

            int id = resultSet.getInt("id");
            bikeStation.setId(id);

            int bikeId = resultSet.getInt("bike_id");
            bikeStation.setBikeId(bikeId);

            int stationId = resultSet.getInt("station_id");
            bikeStation.setStationId(stationId);

            return bikeStation;
        } catch (SQLException e) {
            throw new DAOException("SQLException detected.", e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement,
                                             BikeStation bikeStation) throws DAOException {
        try {
            statement.setInt(1, bikeStation.getBikeId());
            statement.setInt(2, bikeStation.getStationId());
        } catch (SQLException e) {
            throw new DAOException("Can't prepare statement for insert!", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement,
                                             BikeStation bikeStation) throws DAOException {
        try {
            statement.setInt(1, bikeStation.getStationId());
            statement.setInt(2, bikeStation.getBikeId());
        } catch (SQLException e) {
            throw new DAOException("Can't prepare statement for update!", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement,
                                             BikeStation bikeStation) throws DAOException {
        try {
            statement.setInt(1, bikeStation.getBikeId());

        } catch (SQLException e) {
            throw new DAOException("Can't prepare statement for delete!", e);
        }
    }
}
