package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.entity.Bike;
import com.epam.bikeRetail.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that provide access to the database and deal with Bike entity.
 *
 * @author Stepan Menchytsky
 * @see Bike
 * @see AbstractDAO
 */
public class BikeDAO extends AbstractDAO<Bike> {

    private static final String UPDATE_QUERY = "UPDATE bike SET price=? priceOnHour=? brandName=? model=? WHERE id = ?";
    private static final String CREATE_QUERY = "INSERT INTO bike (priceOnHour, brandName, model) VALUES (?,?,?)";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM bike WHERE id=?";
//    private static final String SELECT_QUERY = "SELECT * FROM bike";

    private static final String DELETE_QUERY = "DELETE FROM bike WHERE id=?";

    private static final String SQL_SELECT_BIKE_ID = "SELECT MAX(id) FROM bike ";

    private static final String SELECT_QUERY = "SELECT bike.id, bike.priceOnHour, bike.brandName, bike.model FROM bikestation RIGHT JOIN bike ON bikestation.bike_id = bike.id WHERE bikestation.station_id=?";




    public BikeDAO(Connection connection) {
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

    @Override
    protected Bike build(ResultSet resultSet) throws DAOException {
        try {
            Bike bike = new Bike();

            int id = resultSet.getInt("id");
            bike.setId(id);

            BigDecimal priceOnHour = resultSet.getBigDecimal("priceOnHour");
            bike.setPriceOnHour(priceOnHour);

            String brand = resultSet.getString("brandName");
            bike.setBrand(brand);

            String model = resultSet.getString("model");
            bike.setModel(model);

            return bike;
        }catch (SQLException e){
            throw new DAOException("SQLException detected.", e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Bike bike) throws DAOException {
        try {
            statement.setBigDecimal(1,bike.getPriceOnHour());
            statement.setString(2, bike.getBrand());
            statement.setString(3, bike.getModel());
        } catch (SQLException e){
            throw new DAOException("Can't prepare statement to insert!", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Bike bike) throws DAOException {
        try {
            statement.setBigDecimal(1,bike.getPriceOnHour());
            statement.setString(2, bike.getBrand());
            statement.setString(2, bike.getModel());
        } catch (SQLException e){
            throw new DAOException("Can't prepare statement for update!", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Bike bike) throws DAOException {
        try {
            statement.setInt(1,bike.getId());
        } catch (SQLException e){
            throw new DAOException("Can't prepare statement for update!", e);
        }
    }
}
