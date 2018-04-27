package com.epam.bikeRetail.service;

import com.epam.bikeRetail.dao.BikeDAO;
import com.epam.bikeRetail.dao.BikeStationDAO;
import com.epam.bikeRetail.dao.DAOCreator;
import com.epam.bikeRetail.entity.BikeStation;
import com.epam.bikeRetail.entity.Bike;
import com.epam.bikeRetail.exception.ConnectionException;
import com.epam.bikeRetail.exception.DAOException;
import com.epam.bikeRetail.exception.ServiceException;

import java.util.List;

/**
 * Service class for Bike entity.
 *
 * @author Stepan Menchytcky
 * @see Bike
 */
public class BikeService {
    /**
     * Method find bikes by station id in dataBase.
     *
     * @param id Station id.
     * @return List of bikes on station.
     * @throws ServiceException when SQLException and DAOException detected.
     */
    public List<Bike> showBikes(String id) throws ServiceException{
        List<Bike> bikes;

        try(DAOCreator daoCreator = new DAOCreator()) {

            BikeDAO bikeDAO = daoCreator.getBikeDAO();

            int stationId = Integer.parseInt(id);
            bikes = bikeDAO.getAllById(stationId);
        }catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }

        return bikes;
    }

    /**
     * Method find all bikes in dataBase.
     *
     * @return List of bikes on station.
     * @throws ServiceException when SQLException and DAOException detected.
     */
    public List<Bike> showAllBikes() throws ServiceException{
        List<Bike> bikes;

        try(DAOCreator daoCreator = new DAOCreator()) {

            BikeDAO bikeDAO = daoCreator.getBikeDAO();

            bikes = bikeDAO.getAll();
        }catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }

        return bikes;
    }

    public void addBike(Bike bike, String stationId) throws ServiceException{
        try(DAOCreator daoCreator = new DAOCreator()) {
            daoCreator.startTransaction();

            BikeDAO bikeDAO = daoCreator.getBikeDAO();
            BikeStationDAO bikeStationDAO = daoCreator.getBikeStationDAO();

            bike = bikeDAO.insert(bike);

            BikeStation bikeStation = new BikeStation();
            bikeStation.setStationId(Integer.parseInt(stationId));
            bikeStation.setBikeId(bike.getId());

            bikeStationDAO.insert(bikeStation);

            daoCreator.commitTransaction();

        } catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }

    public void deleteBike(String id, BikeStation bikeStation) throws ServiceException{
        int bikeId = Integer.parseInt(id);

        try(DAOCreator daoCreator = new DAOCreator()) {
            daoCreator.startTransaction();

            BikeDAO bikeDAO = daoCreator.getBikeDAO();
            BikeStationDAO bikeStationDAO = daoCreator.getBikeStationDAO();

            bikeStationDAO.delete(bikeStation);

            Bike bike = bikeDAO.getById(bikeId);
            bikeDAO.delete(bike);

            daoCreator.commitTransaction();
        } catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }

    public void moveBike(BikeStation bikeStation) throws ServiceException {
        try(DAOCreator daoCreator = new DAOCreator()) {

            BikeStationDAO bikeStationDAO = daoCreator.getBikeStationDAO();

            bikeStationDAO.update(bikeStation);
        }catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }
}
