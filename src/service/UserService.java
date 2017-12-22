package service;

import java.util.List;

import domain.User;

public interface UserService {
    User findById(Long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    void save(User user) throws ServiceException;
}
