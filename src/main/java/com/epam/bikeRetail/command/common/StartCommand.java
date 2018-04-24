package com.epam.bikeRetail.command.common;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class StartCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.login");
    }
}
