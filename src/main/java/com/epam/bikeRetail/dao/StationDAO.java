package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.entity.Station;
import com.epam.bikeRetail.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that provide access to the database and deal with User entity.
 *
 * @author Stepan Menchytsky
 * @see Station
 * @see AbstractDAO
 */
public class StationDAO extends AbstractDAO<Station> {

    private static final String UPDATE_QUERY = "UPDATE station SET  nameStation=?, WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO station (id, nameStation,) VALUES (?, ?)";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM station WHERE id=?";
    private static final String SELECT_QUERY = "SELECT * FROM station";

    public StationDAO(Connection connection) {
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
        return null;
    }

    @Override
    protected Station build(ResultSet resultSet) throws DAOException {
        try{
            Station station = new Station();

            int id = resultSet.getInt("id");
            station.setId(id);

            String nameStation = resultSet.getString("nameStation");
            station.setNameStation(nameStation);

            return station;
        } catch (SQLException e) {
            throw new DAOException("SQLException detected.", e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Station station) throws DAOException {
        try {
            statement.setInt(1, station.getId());
            statement.setString(2, station.getNameStation());
        } catch (SQLException e){
            throw new DAOException("Can't prepare statement to insert!", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Station station) throws DAOException {
        try {
            statement.setString(2, station.getNameStation());
        } catch (SQLException e){
            throw new DAOException("Can't prepare statement to insert!", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Station object) throws DAOException {

    }
}
