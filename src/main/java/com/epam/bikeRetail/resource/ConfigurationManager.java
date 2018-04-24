package com.epam.bikeRetail.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static String RESOURCES = "config";

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCES);

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
