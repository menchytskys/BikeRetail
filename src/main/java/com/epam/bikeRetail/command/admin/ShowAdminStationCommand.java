package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.Bike;
import com.epam.bikeRetail.entity.Station;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.BikeService;
import com.epam.bikeRetail.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAdminStationCommand implements ActionCommand {
    private final Logger LOGGER = LogManager.getLogger(ShowAdminStationCommand.class.getName());
    private final static String ADMIN_STATIONS_PAGE = "path.page.adminStation";
    private final static String ERROR_PAGE = "path.page.error";
    private static final String PARAM_STATION_ID = "id";
    private final static String STATIONS_ATTRIBUTE = "station";
    private final static String BIKES_ATTRIBUTE = "bikes";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Station station = null;
        List<Bike> bikes = null;

        String stationId = request.getParameter(PARAM_STATION_ID);

        StationService stationService = new StationService();
        BikeService bikeService = new BikeService();

        try{
            station = stationService.findStationById(stationId);
            bikes = bikeService.showBikes(stationId);

            if(station != null){
                page =  ConfigurationManager.getProperty(ADMIN_STATIONS_PAGE);
            } else {
                page = ConfigurationManager.getProperty(ERROR_PAGE);
            }
        } catch (ServiceException e){
            LOGGER.error("Service exception detected.", e);
        }

        request.setAttribute(STATIONS_ATTRIBUTE, station);
        request.setAttribute(BIKES_ATTRIBUTE, bikes);

        return page;
    }

}
