package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeUserStatusCommand implements ActionCommand {
    private final Logger LOGGER = LogManager.getLogger(ChangeUserStatusCommand.class.getName());
    private static final String PARAM_USER = "user";
    private static final String ERROR_PAGE = "path.page.error";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        try {
            User user = (User)request.getAttribute(PARAM_USER);

            UserService userService = new UserService();

            if(user.getActiveStatus() == 1){
                user.setActiveStatus(0);
                userService.changeUserStatus(user);
            } else {
                user.setActiveStatus(1);
                userService.changeUserStatus(user);
            }
        } catch (ServiceException e){
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }
        return null;
    }
}
