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
        String sql = "select * from product order by pflag,cid";
        return template.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
    }

    @Override
    public void save(Product product) {
        String sql = "insert into product values (?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, product.getPid(), product.getPname(),
                product.getMarket_price(), product.getShop_price(),
                product.getPimage(), product.getPdate(),
                product.getIs_hot(), product.getPdesc(),
                product.getPflag(), product.getCategory().getCid());
    }

    @Override
    public void delete(String pname) {
        //String sql = "delete from product where pname = ?";
        String sql = "update product set pflag = 1 where pname = ?";
        template.update(sql, pname);
    }

    @Override
    public void edit(Product product) {

    }

    @Override
    public Product findByPname(String pname) {
        String sql = "select * from product where pname = ?";
        try {
            Product product = template.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class), pname);
            return product;
        } catch (Exception e) {
            return null;
        }
    }

}
