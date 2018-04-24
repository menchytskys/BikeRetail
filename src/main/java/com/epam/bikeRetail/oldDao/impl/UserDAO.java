package com.epam.bikeRetail.oldDao.impl;

import com.epam.bikeRetail.entityes.Entity;
import com.epam.bikeRetail.entityes.RentBike;
import com.epam.bikeRetail.entityes.user.User;
import com.epam.bikeRetail.entityes.user.UserRole;
import com.epam.bikeRetail.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of DAO level provides access to database and work with User entity.
 */
public class UserDAO extends AbstractDAO {

    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user WHERE roles='USER'";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM user WHERE login=? and password=?";
    private static final String SQL_INSERT_INTO_RENTBIKE =
            "INSERT INTO rentbike (rentTime, user_id, bike_id) VALUES (?,?,?)";
    private static final String SQL_SELECT_RENTBIKE = "SELECT * FROM rentbike WHERE user_id=?";
    private static final String SQL_UPDATE_USER_RENTSTATUS = "UPDATE user SET rentStatus=? WHERE id=?";
    private static final String SQL_UPDATE_USER_BALANCE = "UPDATE user SET name=?, lastName=?, login=?, password=?, roles=?, balance=?, rentStatus=?, activeStatus=? WHERE id=?";
    private static final String SQL_DELETE_USER_DATA_IN_RENTBIKE = "DELETE FROM rentbike WHERE user_id=?";
    /**
     * Instantiates a new UserDAO.
     *
     * @param connection the connection to database.
     */
    public UserDAO(Connection connection) {
        super(connection);
    }

    /**
     * This method find all Users.
     *
     * @return List of Users.
     */
    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);

            while (resultSet.next()){

                User user = new User();

                int id = resultSet.getInt("id");
                user.setId(id);

                String name = resultSet.getString("name");
                user.setName(name);

                String lastName = resultSet.getString("lastName");
                user.setLastName(lastName);

                String login = resultSet.getString("login");
                user.setLogin(login);

                String password = resultSet.getString("password");
                user.setPassword(password);

                String userTypeValue = resultSet.getString("roles")
                        .toUpperCase();
                UserRole userRole = UserRole.valueOf(userTypeValue);
                user.setUserRole(userRole);

                double balance = resultSet.getDouble("balance");
                user.setBalance(balance);

                int rentStatus = resultSet.getInt("rentStatus");
                user.setRentStatus(rentStatus);

                int activeStatus = resultSet.getInt("activeStatus");
                user.setActiveStatus(activeStatus);

                users.add(user);
            }
        }catch (SQLException e){
            throw new DAOException ("SQL exception detected.", e);
        }finally {
            close(statement);
        }

        return users;
    }

    public void deleteDataInRentbike(String userId) throws DAOException {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_DATA_IN_RENTBIKE);
            preparedStatement.setString(1, userId);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }
    /**
     * Method to finds User by login and password
     *
     * @param login the User login.
     * @param password the User password.
     * @return User
     * @throws DAOException object if execution of query is failed.
     */
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return createUser(resultSet);
        }catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }

    public void takeBike(String bikeId, int userId, String rentTime) throws DAOException {
        String userIdStr = Integer.toString(userId);

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_RENTBIKE);
            preparedStatement.setString(1, rentTime);
            preparedStatement.setString(2, userIdStr);
            preparedStatement.setString(3, bikeId);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }

    public void updateUserBalance(double priceForRent, String userId) throws DAOException {
        String newBalance = Double.toString(priceForRent);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BALANCE);
            preparedStatement.setString(1, newBalance);
            preparedStatement.setString(2, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }

    public void updateUserIsTakeBike(String userId, int rentStatus) throws DAOException {
        String rentStatusStr = Integer.toString(rentStatus);

        try {
            PreparedStatement preparedStatementFirst = connection.prepareStatement(SQL_UPDATE_USER_RENTSTATUS);
            preparedStatementFirst.setString(1,rentStatusStr);
            preparedStatementFirst.setString(2,userId);

            preparedStatementFirst.executeUpdate();
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }
    public RentBike returnBike(String userId, String stationId) throws DAOException {

        try {
            PreparedStatement preparedStatementSecond = connection.prepareStatement(SQL_SELECT_RENTBIKE);
            preparedStatementSecond.setString(1,userId);

            ResultSet resultSet = preparedStatementSecond.executeQuery();

            resultSet.next();

            RentBike rentBike = new RentBike();

            String rentTimeStr = resultSet.getString("rentTime");
            int rentTime = Integer.parseInt(rentTimeStr);
            rentBike.setRentTime(rentTime);

            int bikeId = resultSet.getInt("bike_id");
            rentBike.setBikeId(bikeId);

            rentBike.setUserId(Integer.parseInt(userId));

            return rentBike;
        } catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }

    @Override
    public Entity findEntityById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {
    }

    @Override
    public Entity update(Entity entity) {
        return null;
    }

    private User createUser(ResultSet resultSet) throws DAOException{
        try{
            User user = null;

            if(resultSet.next()) {

                user = new User();

                int id = resultSet.getInt("id");
                user.setId(id);

                String name = resultSet.getString("name");
                user.setName(name);

                String lastName = resultSet.getString("lastName");
                user.setLastName(lastName);

                String login = resultSet.getString("login");
                user.setLogin(login);

                String password = resultSet.getString("password");
                user.setPassword(password);

                String userTypeValue = resultSet.getString("roles")
                        .toUpperCase();
                UserRole userRole = UserRole.valueOf(userTypeValue);
                user.setUserRole(userRole);

                double balance = resultSet.getDouble("balance");
                user.setBalance(balance);

                int rentStatus = resultSet.getInt("rentStatus");
                user.setRentStatus(rentStatus);

                int activeStatus = resultSet.getInt("activeStatus");
                user.setActiveStatus(activeStatus);
            }
            return user;
        }catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }
}
