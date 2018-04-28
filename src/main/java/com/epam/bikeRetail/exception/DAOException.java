package com.epam.bikeRetail.exception;

/**
 * The type of checked exception that thrown from DAO-level.
 *
 * @author Stepan Menchytskiy
 */
public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
