package com.epam.bikeRetail.command.common;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.entity.UserRole;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.Locale;

import static com.epam.bikeRetail.resource.MessageManager.DEFAULT_LOCALE;

/**
 * Command to change language.
 *
 * @author Stepan Menchytsky
 */
public class ChangeLanguageCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.
            class.getName());

    private static final String RU_LANGUAGE = "ru";
    private static final String US_LANGUAGE = "en";
    private static final String BY_LANGUAGE = "by";

    private static final String RU_COUNTRY = "RU";
    private static final String US_COUNTRY = "US";
    private static final String BY_COUNTRY = "BY";

    private static final String LOCALE_PARAM = "locale";
    private static final String USER_ATTRIBUTE = "user";

    private static final String MAIN_PAGE_PATH = "path.page.main";


    /**
     * Implementation of commands to change language.
     *
     * @param request HttpServletRequest object.
     * @return redirect page.
     */
    @Override
    public String execute(HttpServletRequest request) {

        String localeValue = request.getParameter(LOCALE_PARAM);
        Locale locale;
        switch (localeValue) {
            case RU_LANGUAGE: {
                locale = new Locale(RU_LANGUAGE, RU_COUNTRY);
                break;
            }
            case US_LANGUAGE: {
                locale = new Locale(US_LANGUAGE, US_COUNTRY);
                break;
            }
            case BY_LANGUAGE: {
                locale = new Locale(BY_LANGUAGE, BY_COUNTRY);
                break;
            }
            default: {
                locale = DEFAULT_LOCALE;
                break;
            }
        }
        Config.set(request.getSession(), Config.FMT_LOCALE, locale);
        MessageManager.changeLocale(locale);

        LOGGER.info(String.format("Language was changed successful. Current language is - %s.", localeValue));

        return ConfigurationManager.getProperty(MAIN_PAGE_PATH);
    }
}

