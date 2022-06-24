package Dao;

import domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll();
    public void delete(String string);
    public void edit(User user);
    public User adminFindByUsername(String username);
}
