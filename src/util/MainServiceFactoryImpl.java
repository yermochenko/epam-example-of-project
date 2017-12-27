package util;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDao;
import dao.mysql.UserDaoImpl;
import service.UserService;
import service.logic.UserServiceImpl;

public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDefaultPassword("12345");
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null) {
            try {
                connection = Connector.getConnection();
            } catch(SQLException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch(Exception e) {}
    }
}
