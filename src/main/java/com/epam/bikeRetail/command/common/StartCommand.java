package com.epam.bikeRetail.command.common;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class StartCommand implements ActionCommand {
    private static final String PATH_PAGE_LOGIN = "path.page.login";

    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
    }
}
