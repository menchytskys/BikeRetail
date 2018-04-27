package com.epam.bikeRetail.command.admin;

import com.epam.bikeRetail.command.ActionCommand;

import com.epam.bikeRetail.entity.Station;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.resource.ConfigurationManager;
import com.epam.bikeRetail.service.StationService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

//delete

public class ShowAllStationsCommand implements ActionCommand {

    private final static String STATIONS_PAGE = "path.page.stations";
    private final static String LIST_ATTRIBUTE = "list";
    private final static String ERROR_PAGE = "path.page.error";

    @Override
    public String execute(HttpServletRequest request){
        String page;
        List<Station> stations = null;

        StationService stationService = new StationService();

        try {
            stations = stationService.showAllStations();
        }catch (ServiceException e){
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }

        request.setAttribute(LIST_ATTRIBUTE, stations);

        page = ConfigurationManager.getProperty(STATIONS_PAGE);

        return page;
    }
}
