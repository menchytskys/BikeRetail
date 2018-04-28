package com.epam.bikeRetail.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Action command. Realization of pattern - Command.
 *
 * @author Stepan Menchytsky
 * @see HttpServletRequest
 */
public interface ActionCommand {

    /**
     * Need to be implemented by command classes.
     *
     * @param request HttpServletRequest object.
     * @return redirect page.
     * @see HttpServletRequest
     */
    String execute(HttpServletRequest request);

}
