package com.epam.bikeRetail.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MessageManager handles the storage and retrieval of the messages.
 *
 * @author Stepan Menchytsky
 */
public class MessageManager {
    public static final Locale DEFAULT_LOCALE = new Locale("", "");
    private static final  String RESOURCES = "messages";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCES, DEFAULT_LOCALE);

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

    /**
     * Change language of jsp page.
     *
     * @param locale the locale.
     */
    public static void changeLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCES, locale);
    }
}
