package service.logic;

import service.Transaction;

public class BaseService {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
