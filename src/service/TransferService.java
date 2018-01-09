package service;

import domain.User;

public interface TransferService {
    void transfer(Long sourceId, Long destinationId, Long amount, User operator) throws ServiceException;
}
