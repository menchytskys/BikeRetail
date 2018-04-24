package com.epam.bikeRetail.command.user;

import com.epam.bikeRetail.command.ActionCommand;
import com.epam.bikeRetail.entity.Bike;
import com.epam.bikeRetail.entity.Station;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.BikeService;
import com.epam.bikeRetail.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowUserStationCommand implements ActionCommand {
    private final Logger LOGGER = LogManager.getLogger(ShowUserStationCommand.class.getName());
    private final static String STATIONS_USER_PAGE = "path.page.userStation";
    private final static String ERROR_PAGE = "path.page.error";
    private static final String PARAM_NAME_ID = "id";
    private final static String STATION_ATTRIBUTE = "station";
    private final static String BIKES_ATTRIBUTE = "bikes";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Station station = null;
        List<Bike> bikes = null;
        User user = null;

        String stationId = request.getParameter(PARAM_NAME_ID);

        BikeService bikeService = new BikeService();
        StationService stationService = new StationService();

        try {

//            HttpSession currentSession = request.getSession();
//            user = (User) currentSession.getAttribute("user");

            bikes = bikeService.showBikes(stationId);
            station = stationService.findStationById(stationId);

            if (station != null) {
                page = ConfigurationManager.getProperty(STATIONS_USER_PAGE);
            } else {
                page = ConfigurationManager.getProperty(ERROR_PAGE);
            }
        } catch (ServiceException e) {
            LOGGER.error("Service exception detected.", e);
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        request.setAttribute(STATION_ATTRIBUTE, station);
        request.setAttribute(BIKES_ATTRIBUTE, bikes);
//        user.isTakeBike();
//        request.setAttribute("user", user);

        return page;
    }
}
