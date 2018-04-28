package com.epam.bikeRetail.exception;

/**
 * The type of checked exception thrown on service level
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
