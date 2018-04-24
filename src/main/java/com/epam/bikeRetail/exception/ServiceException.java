package com.epam.bikeRetail.exception;

/**
 * The Exception thrown on service level
 *
 * @author Stepan Menchytsky
 * @see Exception
 */
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
