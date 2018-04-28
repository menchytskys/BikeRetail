package com.epam.bikeRetail.resource;

import java.util.ResourceBundle;

/**
 * ConfigurationManager handles the storage and retrieval of the jsp pages.
 *
 * @author Stepan Menchytsky
 */
public class ConfigurationManager {
    private final static String RESOURCES = "config";

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCES);

    private ConfigurationManager() {
    }

    /**
     * Return path to jsp page.
     *
     * @param key key to path.
     * @return path to jsp page.
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
