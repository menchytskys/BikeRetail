package com.epam.bikeRetail.command;

import com.epam.bikeRetail.command.common.StartCommand;
import com.epam.bikeRetail.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory class for creation commands.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 */
public class ActionFactory {

    private static final String MESSAGE_WRONG_ACTION = "message.wrongAction";
    private static final String WRONG_ACTION_ATTRIBUTE = "wrongAction";

    /**
     * This method define command and return it's instance.
     *
     * @param request the HttpServletRequest request.
     * @return the defined command.
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new StartCommand();

        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            String commandTypeValue = action.toUpperCase();
            CommandEnum currentEnum = CommandEnum.valueOf(commandTypeValue);
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(WRONG_ACTION_ATTRIBUTE,
                    action + MessageManager.getProperty(MESSAGE_WRONG_ACTION));
        }
        return current;
    }
}
