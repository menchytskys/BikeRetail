package com.epam.bikeRetail.exception;

/**
 * The type of checked exception that thrown from ConnectionPool.
 *
 * @author Stepan Menchytskiy
 */
public class ConnectionException extends Exception {
    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
