package util;

import java.sql.Connection;

import dao.UserDao;
import service.UserService;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
