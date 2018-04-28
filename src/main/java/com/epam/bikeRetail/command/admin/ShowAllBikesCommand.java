package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.Bike;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.BikeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command to show all bikes.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 * @see HttpServletRequest
 */
public class ShowAllBikesCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(ShowAllBikesCommand.
            class.getName());
    private final static String BIKES_PAGE = "path.page.bikes";
    private final static String LIST_ATTRIBUTE = "bikes";
    private final static String ERROR_PAGE = "path.page.error";

    /**
     * Implementation of command to show all bikes.
     *
     * @param request HttpServletRequest object.
     * @return redirect page.
     */
    @Override
    public String execute(HttpServletRequest request) {

        String page;
        List<Bike> bikes = null;

        BikeService bikeService = new BikeService();

        try {
            bikes = bikeService.showAllBikes();
        } catch (ServiceException e) {
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        request.setAttribute(LIST_ATTRIBUTE, bikes);

        page = ConfigurationManager.getProperty(BIKES_PAGE);

        return page;
    }
}
