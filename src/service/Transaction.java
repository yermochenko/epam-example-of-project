package service;

public interface Transaction {
    void start() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
