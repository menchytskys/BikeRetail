package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllUsersCommand implements ActionCommand {
    private final static String USERS_PAGE = "path.page.users";
    private final static String ERROR_PAGE = "path.page.error";
    private final static String LIST_ATTRIBUTE = "list";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        List<User> users = null;

        UserService userService = new UserService();

        try {
            users = userService.showAllUsers();
        }catch (ServiceException e){
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        request.setAttribute(LIST_ATTRIBUTE, users);

        page = ConfigurationManager.getProperty(USERS_PAGE);

        return page;
    }
}
