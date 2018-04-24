package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.exception.DAOException;

import java.util.List;

/**
 * Unified object to control the perception of objects
 *
 * @param <T> the type of the persistence's object
 * @author Stepan Menchytsky
 */
public interface GenericDAO<T extends Identifiable> {

    /**
     * Create new row in DB about object
     */
    T insert(T object) throws DAOException;

    /**
     * Return object with row where key or null
     */
    T getById(Integer key) throws DAOException;

    /**
     * Save persistence of object in DB
     */
     void update(T object) throws DAOException;

    /**
     * delete persistence of object in DB
     */
    void delete(T object) throws DAOException;

    /**
     *  Get list of objects which are rows in DB
     */
     List<T> getAll() throws DAOException;

    /**
     *  Get list of objects which are rows in DB
     */
     List<T> getAllById(Integer key) throws DAOException;
}
