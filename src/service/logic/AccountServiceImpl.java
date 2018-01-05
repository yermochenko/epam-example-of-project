package service.logic;

import java.util.List;

import dao.AccountDao;
import dao.DaoException;
import dao.TransferDao;
import dao.UserDao;
import domain.Account;
import domain.Transfer;
import service.AccountService;
import service.ServiceException;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;
    private TransferDao transferDao;
    private UserDao userDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransferDao(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Account findById(Long id) throws ServiceException {
        try {
            Account account = accountDao.read(id);
            if(account != null) {
                List<Transfer> history = transferDao.readHistoryOfAccount(id);
                Account transferAccount;
                for(Transfer transfer : history) {
                    transferAccount = transfer.getSource();
                    if(transferAccount != null && transferAccount.getId() != null) {
                        transferAccount = accountDao.read(transferAccount.getId());
                        transfer.setSource(transferAccount);
                    }
                    transferAccount = transfer.getDestination();
                    if(transferAccount != null && transferAccount.getId() != null) {
                        transferAccount = accountDao.read(transferAccount.getId());
                        transfer.setDestination(transferAccount);
                    }
                    transfer.setOperator(userDao.read(transfer.getOperator().getId()));
                }
                account.setHistory(history);
            }
            return account;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
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
