package com.epam.bikeRetail.service;
import com.epam.bikeRetail.dao.DAOCreator;
import com.epam.bikeRetail.dao.StationDAO;
import com.epam.bikeRetail.entity.Station;
import com.epam.bikeRetail.exception.ConnectionException;
import com.epam.bikeRetail.exception.DAOException;
import com.epam.bikeRetail.exception.ServiceException;

import java.util.List;

/**
 * Service class for Station entity.
 *
 * @author Stepan Menchytcky
 * @see Station
 */
public class StationService {

    /**
     * Method find all stations in data base.
     *
     * @return Collection os Station.
     * @throws ServiceException when SQLException and DAOException detected.
     */
    public List<Station> showAllStations() throws ServiceException {
        List<Station> stations;

        try (DAOCreator daoCreator = new DAOCreator()) {

            StationDAO stationDAO = daoCreator.getStationDAO();

            stations = stationDAO.getAll();
        }catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }

        return stations;
    }

    /**
     * Method find station in database by id.
     *
     * @param id station id.
     * @return Object Station.
     * @throws ServiceException when SQLException and DAOException detected.
     */
    public Station findStationById(String id) throws ServiceException{
        Station station;

        try(DAOCreator daoCreator = new DAOCreator()) {
            StationDAO stationDAO = daoCreator.getStationDAO();

            int stationId = Integer.parseInt(id);
            station = stationDAO.getById(stationId);
        } catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }

        return station;
    }
}
