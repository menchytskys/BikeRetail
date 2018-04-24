package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.entity.RentBike;
import com.epam.bikeRetail.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that provide access to the database and deal with RentBike entity.
 *
 * @author Stepan Menchytsky
 * @see RentBike
 * @see AbstractDAO
 */
public class RentBikeDAO extends AbstractDAO<RentBike> {

    private static final String UPDATE_QUERY = "UPDATE rentbike SET rentTime=? userId=? bikeId=?  WHERE id = ?";
    private static final String CREATE_QUERY = "INSERT INTO rentbike (rentTime, userId, bikeId) VALUES (?,?,?)";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM rentbike WHERE userId=?";
    private static final String SELECT_QUERY = "SELECT * FROM rentbike";
    private static final String DELETE_QUERY = "DELETE FROM rentbike WHERE userId=?";

    public RentBikeDAO(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getQueryById() {
        return SELECT_QUERY_BY_ID;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

//    public  deleteUserDataInRentbike(int userId ) throws DAOException {
//        User user = executeQueryForSingleResult(FIND_BY_LOGIN_PASSWORD, login, password);
//        return user;
//    }

    @Override
    protected RentBike build(ResultSet resultSet) throws DAOException {
        try {
            RentBike rentBike = new RentBike();

            int rentTime = resultSet.getInt("rentTime");
            rentBike.setRentTime(rentTime);

            int userId = resultSet.getInt("userId");
            rentBike.setUserId(userId);

            int bikeId = resultSet.getInt("bikeId");
            rentBike.setBikeId(bikeId);

            return rentBike;
        } catch (SQLException e) {
            throw new DAOException("SQLException detected.", e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, RentBike rentBike) throws DAOException {
        try {
            statement.setInt(1, rentBike.getRentTime());
            statement.setInt(2, rentBike.getUserId());
            statement.setInt(3, rentBike.getBikeId());
        } catch (SQLException e){
            throw new DAOException("Can't prepare statement to insert!", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, RentBike rentBike) throws DAOException {
        try {
            statement.setInt(1, rentBike.getRentTime());
            statement.setInt(2, rentBike.getUserId());
            statement.setInt(3, rentBike.getBikeId());
        } catch (SQLException e){
            throw new DAOException("Can't prepare statement to insert!", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, RentBike rentBike) throws DAOException {
        try {
            statement.setInt(1, rentBike.getUserId());

        } catch (SQLException e){
            throw new DAOException("Can't prepare statement to insert!", e);
        }
    }
}
