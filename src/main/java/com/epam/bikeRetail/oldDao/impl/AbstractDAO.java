package com.epam.bikeRetail.oldDao.impl;

import com.epam.bikeRetail.entityes.Entity;
import com.epam.bikeRetail.exception.DAOException;
import com.epam.bikeRetail.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <T extends Entity> {
    private final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DAOException;
    public abstract T findEntityById(String id) throws DAOException;
    public abstract void deleteById(String id) throws DAOException;
    public abstract T update(T entity);

    public void close(Statement st){
        try{
            if(st != null){
                st.close();
            }
        }catch (SQLException e){
            LOGGER.info("Can't close Statement", e);
        }
    }
}
