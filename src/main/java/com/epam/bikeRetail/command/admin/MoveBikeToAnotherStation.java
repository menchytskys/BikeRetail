package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.BikeStation;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.BikeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class MoveBikeToAnotherStation implements ActionCommand {

    private final Logger LOGGER = LogManager.getLogger(MoveBikeToAnotherStation.class.getName());
    private final static String ADMIN_STATIONS_PAGE = "path.page.adminStation";
    private final static String ERROR_PAGE = "path.page.error";
    private static final String BIKE_ID = "bikeId";
    private static final String TO_STATION_ID = "station";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
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
