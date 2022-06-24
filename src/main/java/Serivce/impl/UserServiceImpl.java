package Serivce.impl;

import Dao.Impl.UserDaoImpl;
import Dao.UserDao;
import Serivce.UserService;
import domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void delete(String username) {
        userDao.delete(username);
    }

    @Override
    public User adminFindByUsername(String username) {
        return userDao.adminFindByUsername(username);
    }

}
