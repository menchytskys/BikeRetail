package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.BikeStation;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.BikeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteBikeCommand implements ActionCommand {
    private static final String PARAM_STATION_ID = "stationId";
    private final Logger LOGGER = LogManager.getLogger(DeleteBikeCommand.class.getName());
    private final static String ERROR_PAGE = "path.page.error";
    private final static String ADMIN_STATIONS_PAGE = "path.page.adminStation";
   // private final static String ADMIN_STATIONS_PAGE = "/Controller?command=show_admin_station";
    private static final String PARAM_BIKE_ID= "bikeId";


    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String bikeId = request.getParameter(PARAM_BIKE_ID);

        String stationId = request.getParameter(PARAM_STATION_ID);

        BikeStation bikeStation = new BikeStation();
        bikeStation.setBikeId(Integer.parseInt(bikeId));
        bikeStation.setStationId(Integer.parseInt(stationId));

        BikeService bikeService = new BikeService();

        try {
            bikeService.deleteBike(bikeId,bikeStation);

            page = ConfigurationManager.getProperty(ADMIN_STATIONS_PAGE);
        } catch (ServiceException e){
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        return page;
    }
}
