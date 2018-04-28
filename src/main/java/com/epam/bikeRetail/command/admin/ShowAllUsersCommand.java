package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command to show all users.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 * @see HttpServletRequest
 */
public class ShowAllUsersCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(ShowAllUsersCommand.
                                         class.getName());
    private static final String USERS_PAGE = "path.page.users";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String LIST_ATTRIBUTE = "list";

    /**
     * Implementation of command to show all users.
     *
     * @param request HttpServletRequest object.
     * @return redirect page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<User> users = null;

        UserService userService = new UserService();

        try {
            users = userService.showAllUsers();
        } catch (ServiceException e) {
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        request.setAttribute(LIST_ATTRIBUTE, users);

        page = ConfigurationManager.getProperty(USERS_PAGE);

        return page;
    }
}
