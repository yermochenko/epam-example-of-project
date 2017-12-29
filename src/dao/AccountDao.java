package dao;

import java.util.List;

import domain.Account;

public interface AccountDao extends Dao<Account> {
    List<Account> readAll() throws DaoException;
}
