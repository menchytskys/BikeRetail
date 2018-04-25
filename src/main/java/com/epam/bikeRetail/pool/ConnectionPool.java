package com.epam.bikeRetail.pool;

import com.epam.bikeRetail.exception.ConnectionException;
import com.epam.bikeRetail.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread safe connections pool.
 *
 * @author Stepan Menchytsky
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());
    private static final Lock LOCK = new ReentrantLock();

    private static ConnectionPool instance = null;
    private static AtomicBoolean isInstanceExist = new AtomicBoolean(false);

    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle("database");
    private static final String POOL_SIZE = RESOURCE.getString("db.poolsize");
    private static final int INT_POOL_SIZE = Integer.parseInt(POOL_SIZE);
    private final Semaphore semaphore = new Semaphore(INT_POOL_SIZE, true);

    private static final String URL = RESOURCE.getString("db.url");
    private static final String USER = RESOURCE.getString("db.user");
    private static final String PASS = RESOURCE.getString("db.password");
    private static final String DRIVER = RESOURCE.getString("db.driver");

    private final Queue<Connection> connections = new LinkedList<>();

    private ConnectionPool() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName(DRIVER).newInstance();
        initPoolConnection();
    }

    private void initPoolConnection() throws SQLException{

        for(int i = 0; i < INT_POOL_SIZE; i++){
            Connection currentConnection = DriverManager.getConnection(URL, USER, PASS);

            connections.add(currentConnection);
        }
    }

    /**
     * Get instance of connection pool class.
     *
     * @return instance.
     */
    public static ConnectionPool getInstance() throws ConnectionException {
       if(!isInstanceExist.get()){
            LOCK.lock();
            try {
                if (instance == null) {

                    instance = new ConnectionPool();
                    isInstanceExist.set(true);
                }
            }catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e){
                throw new ConnectionException("ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException detected ",e);
            } finally {
                LOCK.unlock();
            }
        }

        return instance;
    }

    /**
     * Get and remove connection from pool.
     *
     * @return first connection from pool.
     */
    public Connection getConnection() {

        try {
            semaphore.acquire();

            LOCK.lock();
            Connection connection = connections.poll();
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Error in a getConnection() , don't available connect", e);
        } finally {
            LOCK.unlock();
        }
    }


    /**
     * Adds chosen connection back to pool.
     *
     * @param connection to database, that was get from pool.
     */
    public void returnConnection(Connection connection){
        LOCK.lock();

        try{
            connections.add(connection);
            semaphore.release();
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Close all connections in pool.
     */
    public void closePool(){
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException exception) {
                LOGGER.error("Can't close all connection in connection pool", exception);
            }
        }
    }
}
