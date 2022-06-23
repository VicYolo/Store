package dao.impl;

import dao.CategoryDao;
import domain.Category;
import domain.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "select * from category";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }

    @Override
    public List<Product> findByHot() {
        String sql = "select * from product where is_hot = 1 and pflag = 0  order by pdate limit ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), 9);
    }

    @Override
    public List<Product> findByNew() {
        String sql = "select * from product where pflag = 0  order by pdate limit ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class),9);
    }
}
