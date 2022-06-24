package Dao.Impl;

import Dao.ProductDao;
import domain.Category;
import domain.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Product> findAll() {
            String sql = "select * from product";
            return template.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(String pname) {
        String sql = "delete from product where pname = ?";
        template.update(sql,pname);
    }

    @Override
    public void edit(Product product) {

    }

    @Override
    public Product findByCname(String cname) {
        return null;
    }
}
