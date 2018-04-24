package com.epam.bikeRetail.command.user;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.RentBike;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class TakeBikeCommand implements ActionCommand {
    private static final int RENT_STATUS_ON = 1;
    private final Logger LOGGER = LogManager.getLogger(TakeBikeCommand.class.getName());
    private final static String USER_PAGE = "path.page.user";
    private final static String ERROR_PAGE = "path.page.error";
    private final static String USER_ATTRIBUTE = "user";

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        String bikeId = request.getParameter("bikeId");
        String rentTime = request.getParameter("rentTime");

        UserService userService = new UserService();
        try {
            HttpSession currentSession = request.getSession();
            User user = (User) currentSession.getAttribute("user");
            user.setRentStatus(RENT_STATUS_ON);

            RentBike rentBike = new RentBike();
            rentBike.setRentTime(Integer.parseInt(rentTime));
            rentBike.setUserId(user.getId());
            rentBike.setBikeId(Integer.parseInt(bikeId));

            userService.takeBike(user, rentBike);

            currentSession.setAttribute(USER_ATTRIBUTE, user);

            page = ConfigurationManager.getProperty(USER_PAGE);
        } catch (ServiceException e){
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        return page;
    }
}
