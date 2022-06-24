package Dao.Impl;

import Dao.UserDao;
import domain.Category;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public void delete(String username) {
        String sql = "delete from user where username = ?";
        template.update(sql, username);
    }

    @Override
    public void edit(User user) {

    }

    @Override
    public User adminFindByUsername(String username) {
        String sql = "select * from user where username = ?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
