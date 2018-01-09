package service.logic;

import java.util.Date;

import dao.AccountDao;
import dao.DaoException;
import dao.TransferDao;
import domain.Account;
import domain.Transfer;
import domain.User;
import service.AccountAmountNotEnoughException;
import service.AccountNotExistsException;
import service.ServiceException;
import service.TransferService;

public class TransferServiceImpl extends BaseService implements TransferService {
    private AccountDao accountDao;
    private TransferDao transferDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransferDao(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @Override
    public void transfer(Long sourceId, Long destinationId, Long amount, User operator) throws ServiceException {
        try {
            getTransaction().start();
            Account source = null;
            if(sourceId != null) {
                source = accountDao.read(sourceId);
                if(source == null) {
                    throw new AccountNotExistsException(sourceId);
                }
            }
            Account destination = null;
            if(destinationId != null) {
                destination = accountDao.read(destinationId);
                if(destination == null) {
                    throw new AccountNotExistsException(destinationId);
                }
            }
            if(source != null) {
                if(source.getBalance() >= amount) {
                    source.setBalance(source.getBalance() - amount);
                    accountDao.update(source);
                } else {
                    throw new AccountAmountNotEnoughException(source, amount);
                }
            }
            if(destination != null) {
                destination.setBalance(destination.getBalance() + amount);
                accountDao.update(destination);
            }
            Transfer transfer = new Transfer();
            transfer.setSource(source);
            transfer.setDestination(destination);
            transfer.setAmount(amount);
            transfer.setDate(new Date());
            transfer.setOperator(operator);
            transferDao.create(transfer);
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }
}
