package dao;

import java.util.List;

import domain.Transfer;

public interface TransferDao extends Dao<Transfer> {
    List<Transfer> readHistoryOfAccount(Long accountId) throws DaoException;
}
