package util;

import java.sql.Connection;
import java.sql.SQLException;

import dao.AccountDao;
import dao.TransferDao;
import dao.UserDao;
import dao.mysql.AccountDaoImpl;
import dao.mysql.TransferDaoImpl;
import dao.mysql.UserDaoImpl;
import service.AccountService;
import service.Transaction;
import service.TransferService;
import service.UserService;
import service.logic.AccountServiceImpl;
import service.logic.TransactionImpl;
import service.logic.TransferServiceImpl;
import service.logic.UserServiceImpl;

public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDefaultPassword("12345");
        userService.setTransaction(getTransaction());
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public AccountService getAccountService() throws FactoryException {
        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.setTransaction(getTransaction());
        accountService.setAccountDao(getAccountDao());
        accountService.setTransferDao(getTransferDao());
        accountService.setUserDao(getUserDao());
        return accountService;
    }

    @Override
    public TransferService getTransferService() throws FactoryException {
        TransferServiceImpl transferService = new TransferServiceImpl();
        transferService.setTransaction(getTransaction());
        transferService.setAccountDao(getAccountDao());
        transferService.setTransferDao(getTransferDao());
        return transferService;
    }

    @Override
    public Transaction getTransaction() throws FactoryException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public AccountDao getAccountDao() throws FactoryException {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        accountDao.setConnection(getConnection());
        return accountDao;
    }

    public TransferDao getTransferDao() throws FactoryException {
        TransferDaoImpl transferDao = new TransferDaoImpl();
        transferDao.setConnection(getConnection());
        return transferDao;
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
