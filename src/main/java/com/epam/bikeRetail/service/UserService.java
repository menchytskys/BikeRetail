package com.epam.bikeRetail.service;

import com.epam.bikeRetail.dao.*;
import com.epam.bikeRetail.entity.BikeStation;
import com.epam.bikeRetail.entity.RentBike;
import com.epam.bikeRetail.entity.Bike;
import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.exception.ConnectionException;
import com.epam.bikeRetail.exception.DAOException;
import com.epam.bikeRetail.exception.ServiceException;
import com.epam.bikeRetail.utils.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for User entity.
 *
 * @author Stepan Menchytcky
 * @see User
 */
public class UserService {
    /**
     * Method that return authorized user.
     *
     * @param login The user login.
     * @param password The user password.
     * @return User
     * @throws ServiceException if execution of method is failed.
     */
    public User login(String login, String password) throws ServiceException {
        User user;

        try(DAOCreator daoCreator = new DAOCreator()) {

            UserDAO userDAO = daoCreator.getUserDAO();
            password = PasswordEncoder.encode(password);

            user = userDAO.findUserByLoginAndPassword(login, password);
        }catch ( DAOException | ConnectionException e){
            throw new ServiceException("Exception detected.", e);
        }

        return user;
    }

    /**
     * Method to show all users
     *
     * @return List of users
     * @throws ServiceException if execution of method is failed.
     */
    public List<User> showAllUsers() throws ServiceException{
        List<User> users;

        try(DAOCreator daoCreator = new DAOCreator()){

            UserDAO userDAO = daoCreator.getUserDAO();

            users = userDAO.getAll();
        }catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }

        return users;
    }

    /**
     * Method change user status.
     *
     * @param id user id.
     * @throws ServiceException if execution of method is failed.
     */
    public void changeUserStatus(String id) throws ServiceException{
        int userId = Integer.parseInt(id);

        try(DAOCreator daoCreator = new DAOCreator()){

            UserDAO userDAO = daoCreator.getUserDAO();

            User user = userDAO.getById(userId);

            if (user.getActiveStatus() == 1) {
                user.setActiveStatus(0);
                userDAO.update(user);
            } else {
                user.setActiveStatus(1);
                userDAO.update(user);
            }
        } catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }

    /**
     * Method Create RentBike and update userRentStatus in database.
     *
     * @param user Entity.
     * @param rentBike Entity.
     * @throws ServiceException if execution of method is failed.
     */
    public void takeBike(User user, RentBike rentBike) throws ServiceException {

        try(DAOCreator daoCreator = new DAOCreator()) {
            daoCreator.startTransaction();

            UserDAO userDAO = daoCreator.getUserDAO();
            RentBikeDAO rentBikeDAO = daoCreator.getRentBikeDAO();

            rentBikeDAO.insert(rentBike);
            userDAO.update(user);

//            userDAO.takeBike(bikeId, userId, rentTime);
//            userDAO.updateUserIsTakeBike(userIdStr, 1);

            daoCreator.commitTransaction();
        } catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }

    /**
     * Method return bike to the station, take money from user for rent and update user status.
     *
     * @param stationId Station Id where user want to return bike.
     * @param user Entity.
     * @throws ServiceException if execution of method is failed.
     */
    public void returnBike(String stationId, User user) throws ServiceException {

        try(DAOCreator daoCreator = new DAOCreator()) {
            daoCreator.startTransaction();

            UserDAO userDAO = daoCreator.getUserDAO();
            RentBikeDAO rentBikeDAO = daoCreator.getRentBikeDAO();
            BikeDAO bikeDAO = daoCreator.getBikeDAO();
            BikeStationDAO bikeStationDAO = daoCreator.getBikeStationDAO();

            int userId = user.getId();
            RentBike rentBike = rentBikeDAO.getById(userId);

            int bikeId = rentBike.getBikeId();
            String bikeIdStr = Integer.toString(bikeId);

            BikeStation bikeStation = new BikeStation(bikeId, Integer.parseInt(stationId));
            bikeStationDAO.update(bikeStation);

            Bike bike = bikeDAO.getById(bikeId);

            int rentTime = rentBike.getRentTime();
            BigDecimal priceOnHour = bike.getPriceOnHour();
            BigDecimal priceForRent = priceOnHour.multiply(new BigDecimal(rentTime));
            BigDecimal balance = user.getBalance();
            BigDecimal newBalance = balance.subtract(priceForRent);

            user.setBalance(newBalance);

            userDAO.update(user);

            rentBikeDAO.delete(rentBike);

            daoCreator.commitTransaction();
        } catch (DAOException | ConnectionException e){
            throw new ServiceException("SQLException and DAOException detected", e);
        }
    }
}
