package com.epam.bikeRetail.resource;

import java.util.ResourceBundle;

/**
 * MessageManager handles the storage and retrieval of the messages.
 *
 * @author Stepan Menchytsky
 */
public class MessageManager {
    private final static String RESOURCES = "messages";

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCES);

    private MessageManager() {
    }

    /**
     * Return massage.
     *
     * @param key key to message.
     * @return message.
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
