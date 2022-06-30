package Serivce;

import domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public void delete(String username);

    public User adminFindByUsername(String username);
}
