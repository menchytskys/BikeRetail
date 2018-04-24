package com.epam.bikeRetail.command.common;


import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.main");

        request.getSession().invalidate();
        return page;

    }
}
