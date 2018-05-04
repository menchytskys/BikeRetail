package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to change user status.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 * @see HttpServletRequest
 */
public class ChangeUserStatusCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(ChangeUserStatusCommand
                                        .class.getName());
    private static final String USERS_PAGE = "/Controller?command=show_all_users";
    private static final String PARAM_USER = "userId";
    private static final String ERROR_PAGE = "path.page.error";

//    private UserService userService;
//
//    public ChangeUserStatusCommand() {
//
//        userService = new UserService();
//    }

    /**
     * Implementation of command for change user status.
     *
     * @param request HttpServletRequest object.
     * @return redirect page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;

        try {
            String id = request.getParameter(PARAM_USER);

            UserService userService = new UserService();

            userService.changeUserStatus(id);

            page = USERS_PAGE;
        } catch (ServiceException e) {
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }
        return page;
    }
}
