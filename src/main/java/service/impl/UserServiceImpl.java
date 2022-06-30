package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(User user){
        User byUsername = userDao.findByUsername(user.getUsername());
        if(byUsername!=null){
            return false;
        }else{
//            System.out.println("Service....准备数据库保存");
            userDao.save(user);
            return true;
        }
    }

    @Override
    public User login(User user) {
//        System.out.println("userService..");
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
