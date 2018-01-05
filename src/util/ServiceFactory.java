package util;

import java.sql.Connection;

import dao.AccountDao;
import dao.TransferDao;
import dao.UserDao;
import service.AccountService;
import service.UserService;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;
    AccountService getAccountService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;
    AccountDao getAccountDao() throws FactoryException;
    TransferDao getTransferDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
