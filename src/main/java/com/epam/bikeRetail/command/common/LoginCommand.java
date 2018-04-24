package com.epam.bikeRetail.command.common;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.entity.UserRole;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.service.UserService;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Command for user log in.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 * @see HttpServletRequest
 */
public class LoginCommand implements ActionCommand{
    private final Logger LOGGER = LogManager.getLogger(LoginCommand.class.getName());
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    private static final String LOGIN_PAGE_PATH = "path.page.login";
    private static final String MAIN_PAGE_PATH = "path.page.main";
    private static final String USER_PAGE_PATH = "path.page.user";
    private static final String ADMIN_PAGE_PATH = "path.page.admin";

    private static final String ERROR_MESSAGE_PATH = "message.loginerror";
    private final static String ERROR_LOGIN_ATTRIBUTE = "loginError";
    private final static String USER_ATTRIBUTE = "user";
    private final static String ERROR_PAGE = "path.page.error";

    /**
     * Implementation of command for user log in
     *
     * @param request HttpServletRequest object
     * @return redirect page
     */
    @Override
    public String execute(HttpServletRequest request){
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        UserService userService = new UserService();
        try{
            User user = userService.login(login, pass);

            if(user != null){
                HttpSession currentSession = request.getSession();
                currentSession.setAttribute(USER_ATTRIBUTE, user);

                page = userRoleRedirect(user);
            } else {
                String errorMessage = MessageManager.getProperty(ERROR_MESSAGE_PATH);
                request.setAttribute(ERROR_LOGIN_ATTRIBUTE, errorMessage);

                page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
            }
        } catch (ServiceException e){
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        return page;
    }

    private String userRoleRedirect(User user){
        UserRole userRole = user.getUserRole();

        switch (userRole) {
            case USER: {
                return ConfigurationManager.getProperty(USER_PAGE_PATH);
            }
            case ADMIN: {
                return ConfigurationManager.getProperty(ADMIN_PAGE_PATH);
            }
            default: {
                return ConfigurationManager.getProperty(MAIN_PAGE_PATH);
            }
        }

    }
}
