package com.epam.bikeRetail.command.common;


import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for user log in.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 * @see HttpServletRequest
 */
public class LogoutCommand implements ActionCommand {
    private static final String MAIN_PAGE_PATH = "path.page.main";

    /**
     * Implementation of command for user log in.
     *
     * @param request HttpServletRequest object
     * @return redirect page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(MAIN_PAGE_PATH);

        request.getSession().invalidate();
        return page;

    }
}
