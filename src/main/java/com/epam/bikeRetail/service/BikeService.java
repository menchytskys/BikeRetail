package com.epam.bikeRetail.service;

import com.epam.bikeRetail.dao.BikeDAO;
import com.epam.bikeRetail.dao.BikeStationDAO;
import com.epam.bikeRetail.dao.DAOCreator;
import com.epam.bikeRetail.entity.BikeStation;
import com.epam.bikeRetail.entity.Bike;
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
     * Method find all bikes in dataBase.
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    public List<Bike> showBikes(String id) throws ServiceException{
        List<Bike> bikes = null;

        try(DAOCreator daoCreator = new DAOCreator()) {

            BikeDAO bikeDAO = daoCreator.getBikeDAO();

            bikes = bikeDAO.getAllById(Integer.parseInt(id));
        }catch (DAOException e){
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

            bikeStation = bikeStationDAO.insert(bikeStation);

            daoCreator.commitTransaction();

        } catch (DAOException e){
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
        } catch (DAOException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }

    public void moveBike(BikeStation bikeStation) throws ServiceException {
        try(DAOCreator daoCreator = new DAOCreator()) {

            BikeStationDAO bikeStationDAO = daoCreator.getBikeStationDAO();

            bikeStationDAO.update(bikeStation);
        }catch (DAOException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }
}
