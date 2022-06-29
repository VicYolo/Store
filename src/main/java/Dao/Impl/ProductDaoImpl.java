package Dao.Impl;

import Dao.ProductDao;
import domain.Category;
import domain.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public void edit(Product product, boolean flag) {
        String sql = "update product set pname = ?, market_price = ?, shop_price = ?, " +
                "is_hot = ?, pdesc = ?, cid = ?, pflag = 0,pdate = ? where pid = ?";
        template.update(sql, product.getPname(), product.getMarket_price(), product.getShop_price(),
                product.getIs_hot(), product.getPdesc(), product.getCategory().getCid(),
                product.getPdate(), product.getPid());
        if (flag) {
            String sqll = "update product set pimage = ? where pid = ?";
            template.update(sqll, product.getPimage(), product.getPid());
        }
    }

    @Override
    public Product findByPname(String pname) {
        String sql = "select * from product where pname = ?";
        try {
            Map<String, Object> map = template.queryForMap(sql, pname);
            Product product = new Product();
            Category category = new Category();
            BeanUtils.populate(product, map);
            //product.setPid((String) map.get("pid"));
            //product.setPname((String) map.get("pname"));
            //product.setPimage((String) map.get("pimage"));
            //product.setPdate((Date) map.get("pdate"));
            //product.setPflag((Integer) map.get("pflag"));
            //product.setIs_hot((Integer) map.get("is_hot"));
            //product.setMarket_price((Double) map.get("market_price"));
            //product.setShop_price((Double) map.get("shop_price"));
            //product.setPdesc((String) map.get("pdesc"));
            category.setCid((String) map.get("cid"));
            product.setCategory(category);
            return product;
        } catch (Exception e) {
            return null;
        }
    }

}
