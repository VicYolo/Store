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

    @Override
    public Product findBypid(String pid) {
        String sql = "select * from product where pid ="+pid;
//        莫名其妙 不知道为什么用queryforobject不行
        List<Product> product = null;
        try{
            product = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
        }catch (Exception e){
            System.out.println("出错了");
        }
        return product.get(0);
    }
}
