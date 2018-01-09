package service.logic;

import java.util.List;

import dao.DaoException;
import dao.UserDao;
import domain.User;
import service.ServiceException;
import service.UserLoginNotUniqueException;
import service.UserNotExistsException;
import service.UserService;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;
    private String defaultPassword;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if(user.getId() != null) {
                User storedUser = userDao.read(user.getId());
                if(storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if(storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                user.setPassword(defaultPassword);
                if(userDao.readByLogin(user.getLogin()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public boolean canDelete(Long id) throws ServiceException {
        try {
            return !userDao.isUserInitiatesTransfers(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
