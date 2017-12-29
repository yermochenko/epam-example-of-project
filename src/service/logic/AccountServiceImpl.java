package service.logic;

import java.util.List;

import dao.AccountDao;
import dao.DaoException;
import domain.Account;
import service.AccountService;
import service.ServiceException;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() throws ServiceException {
        try {
            return accountDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
