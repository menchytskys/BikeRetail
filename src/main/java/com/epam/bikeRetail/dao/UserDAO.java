package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.entity.User;
import com.epam.bikeRetail.entity.UserRole;
import com.epam.bikeRetail.exception.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that provide access to the database and deal with User entity.
 *
 * @author Stepan Menchytsky
 * @see User
 * @see AbstractDAO
 */
public class UserDAO extends AbstractDAO<User> {

    private static final String UPDATE_QUERY =
            "UPDATE user SET name=?, lastName=?, login=?, password=?, roles=?, balance=?, rentStatus=?, activeStatus=? WHERE id=?";
    private static final String CREATE_QUERY =
            "INSERT INTO User (id, name, lastName, login, password, roles, balance, rentStatus, activeStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM User WHERE id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM user WHERE roles='USER'";
    private static final String FIND_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";

    public UserDAO(Connection connection) {
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
        return null;
    }

    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = executeQueryForSingleResult(FIND_BY_LOGIN_PASSWORD, login, password);
        return user;
    }

    @Override
    protected User build(ResultSet resultSet) throws DAOException {
        try {
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

            String userTypeValue = resultSet.getString("roles").toUpperCase();
            UserRole userRole = UserRole.valueOf(userTypeValue);
            user.setUserRole(userRole);

            BigDecimal balance = resultSet.getBigDecimal("balance");
            user.setBalance(balance);

            int rentStatus = resultSet.getInt("rentStatus");
            user.setRentStatus(rentStatus);

            int activeStatus = resultSet.getInt("activeStatus");
            user.setActiveStatus(activeStatus);

            return user;
        } catch (SQLException e) {
            throw new DAOException("SQLException detected.", e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement,
                                             User user) throws DAOException {

        try {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            String role = String.valueOf(user.getUserRole());
            statement.setString(6, role);
            statement.setBigDecimal(7, user.getBalance());
            statement.setInt(8, user.getRentStatus());
            statement.setInt(9, user.getActiveStatus());
        } catch (SQLException e) {
            throw new DAOException("Can't prepare statement to insert!", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement,
                                             User user) throws DAOException {

        try {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            String role = String.valueOf(user.getUserRole());
            statement.setString(5, role);
            statement.setBigDecimal(6, user.getBalance());
            statement.setInt(7, user.getRentStatus());
            statement.setInt(8, user.getActiveStatus());
            statement.setInt(9, user.getId());
        }  catch (SQLException e) {
            throw new DAOException("Can't prepare statement for update!", e);
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement,
                                             User object) throws DAOException {

    }
}
