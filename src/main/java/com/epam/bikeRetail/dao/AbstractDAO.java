package com.epam.bikeRetail.dao;

import com.epam.bikeRetail.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class, realize base method's of CRUD operations to DB.
 *
 * @param <T> object - created form ResultSet or needed to set into DB
 * @author Stepan Menchytsky
 */
public abstract class AbstractDAO<T extends Identifiable> implements GenericDAO<T> {

    private static final int RESULT_OBJECT = 0;

    private Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method, realize SQL-query of selections row's from DB.
     */
    public abstract String getSelectQuery();

    /**
     * Method, realize SQL-query insert object's into DB.
     */
    public abstract String getCreateQuery();

    /**
     * Method, realize SQL-query insert object's into DB.
     */
    public abstract String getQueryById();

    /**
     * Method, realize SQL-query update object's into DB.
     */
    public abstract String getUpdateQuery();

    /**
     * Method, realize SQL-query delete object's into DB.
     */
    public abstract String getDeleteQuery();

    protected abstract T build(ResultSet resultSet) throws DAOException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DAOException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DAOException;

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object) throws DAOException;

    /**
     * This method finds entity from database by id.
     *
     * @param query SQL-query.
     * @param params parameters for search.
     * @return the entity.
     * @throws DAOException if execution of query is failed.
     */
    public T executeQueryForSingleResult(String query, Object... params) throws DAOException {
        List<T> objects = executeQuery(query, params);
        if (objects.isEmpty()) {
            return null;
        }
        T object = objects.get(RESULT_OBJECT);
        return object;
    }

    protected List<T> executeQuery(String query, Object... params) throws DAOException {
        List<T> list = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, params)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(build(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can't execute query!", e);
        }
        return list;
    }

    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    /**
     * Method insert entity in database.
     *
     * @param entity the entity.
     * @return the entity.
     * @throws DAOException if can't insert entity.
     */
    @Override
    public T insert(T entity) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery(),
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(preparedStatement, entity);
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On persist modify more then 1 record: " + count);
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                int id = resultSet.getInt(1);
                entity.setId(id);
            }

        } catch (SQLException e) {
            throw new DAOException("Can't insert " + entity, e);
        }
        return entity;
    }

    /**
     * Method finds entity from database by id.
     *
     * @param id the entity's id.
     * @return the entity.
     * @throws DAOException if execution of query is failed.
     */
    @Override
    public T getById(Integer id) throws DAOException {
        return executeQueryForSingleResult(getQueryById(), id);
    }

    /**
     * This method update entity in database.
     *
     * @param entity the entity.
     * @throws DAOException object if execution of query is failed.
     */
    @Override
    public void update(T entity) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(preparedStatement, entity);
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DAOException("Can not update data from the DB!", e);
        }
    }

    /**
     * This method delete entity in database.
     *
     * @param entity the entity.
     * @throws DAOException object if execution of query is failed.
     */
    @Override
    public void delete(T entity) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())){
            prepareStatementForDelete(preparedStatement, entity);
            int count = preparedStatement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DAOException("Can not delete data from the DB!", e);
        }
    }

    /**
     * This method finds all entities.
     *
     * @return List of found objects.
     * @throws DAOException object if execution of query is failed.
     */
    @Override
    public List<T> getAll() throws DAOException {
        return executeQuery(getSelectQuery());
    }

}
