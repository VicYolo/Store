package service;

import domain.User;

public interface UserService {

    boolean register(User user);

    User login(User user);
}
