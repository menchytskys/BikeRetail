package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.exception.ConnectionException;
import com.epam.bikeRetail.exception.DAOException;
import com.epam.bikeRetail.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class is intended for managing connections, namely the distribution and closing of connections.
 * Also creates instances of Dao objects and puts a connection in them.
 *
 * @author Stepan Menchytsky
 */
public class DAOCreator implements AutoCloseable {

    private ConnectionPool connectionPool;

    private Connection connection;

    public DAOCreator() throws ConnectionException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    /**
     * Method give access to UserDAO
     *
     * @return UserDAO
     */
    public UserDAO getUserDAO() {
        return new UserDAO(connection);
    }

    /**
     * Method give access to UserDAO
     *
     * @return RentBikeDAO
     */
    public RentBikeDAO getRentBikeDAO() {
        return new RentBikeDAO(connection);
    }

    /**
     * Method give access to StationDAO
     *
     * @return StationDAO
     */
    public StationDAO getStationDAO()  {
        return new StationDAO(connection);
    }

    /**
     * Method give access to BikeDAO
     *
     * @return BikeDAO
     */
    public BikeDAO getBikeDAO()  {
        return new BikeDAO(connection);
    }

    /**
     * Method give access to BikeDAO
     *
     * @return BikeDAO
     */
    public BikeStationDAO getBikeStationDAO()  {
        return new BikeStationDAO(connection);
    }

    /**
     * Puts the connection to the connection pool.
     */
    private void returnConnection() {
        connectionPool.returnConnection(connection);
    }

    /**
     * The method allows you to start a transaction.
     */
    public void startTransaction() throws DAOException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
           throw new DAOException("Can't starting date transaction", e);
        }
    }

    /**
     * The method allows you to perform a transaction.
     **/
    public void commitTransaction()throws DAOException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DAOException("Can't committing date transaction", e);
        }
    }

    /**
     * The method to cancel the transaction.
     */
    public void rollbackTransaction() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException("Can't rollback data transaction", e);
        }
    }
    @Override
    public void close() {
        returnConnection();
    }
}
