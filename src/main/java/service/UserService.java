package service;

import domain.User;

public interface UserService {

    boolean register(User user);

    boolean login(User user);
}
