package service;

import java.util.List;

import domain.User;

public interface UserService {
    List<User> findAll() throws ServiceException;
}
