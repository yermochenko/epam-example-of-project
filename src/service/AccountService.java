package service;

import java.util.List;

import domain.Account;

public interface AccountService {
    Account findById(Long id) throws ServiceException;

    List<Account> findAll() throws ServiceException;

    void save(Account account) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
