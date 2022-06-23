package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (EmptyResultDataAccessException e){
        }
        return user;
    }

    @Override
    public void save(User user) {
        System.out.println("数据库插入");
        String sql = "INSERT INTO user(uid,username,password,name,email,telephone,birthday,sex) VALUES(?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql,user.getUid(),user.getUsername(),user.getPassword(), user.getName(),
                user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex());
        System.out.println("数据库插入完毕");
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
//        System.out.println("userDao....");
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        User user = null;
        try{
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
        }catch (Exception e){

        }
//        System.out.println("结束了");
        if(user!=null){
            System.out.println(user.getUsername());
        }
        return user;
    }

}
