package service;

import java.util.List;

import domain.Account;

public interface AccountService {
    List<Account> findAll() throws ServiceException;
}
