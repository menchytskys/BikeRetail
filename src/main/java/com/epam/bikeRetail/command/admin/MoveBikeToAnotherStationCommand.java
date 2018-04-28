package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.BikeStation;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.BikeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to move bike to another station bike.
 *
 * @author Stepan Menchytsky
 * @see ActionCommand
 * @see HttpServletRequest
 */
public class MoveBikeToAnotherStationCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(MoveBikeToAnotherStationCommand.
                                         class.getName());
    private static final String ADMIN_STATIONS_PAGE = "path.page.adminStation";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String BIKE_ID = "bikeId";
    private static final String TO_STATION_ID = "station";

    /**
     * Implementation of command to move bike.
     *
     * @param request HttpServletRequest object.
     * @return redirect page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String bikeId = request.getParameter(BIKE_ID);
        String toStationId = request.getParameter(TO_STATION_ID);

        BikeStation bikeStation = new BikeStation();
        bikeStation.setBikeId(Integer.parseInt(bikeId));
        bikeStation.setStationId(Integer.parseInt(toStationId));

        BikeService bikeService = new BikeService();
        try {
            bikeService.moveBike(bikeStation);
            page = ConfigurationManager.getProperty(ADMIN_STATIONS_PAGE);
        } catch (ServiceException e){
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }
        return page;
    }
}
