package com.epam.bikeRetail.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private final static String RESOURCES = "messages";

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCES);

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
