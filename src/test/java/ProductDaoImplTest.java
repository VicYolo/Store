import Dao.Impl.ProductDaoImpl;
import Dao.ProductDao;
import Serivce.ProductService;
import Serivce.impl.ProductServiceImpl;
import domain.Category;
import domain.Product;
import org.junit.Test;

import java.util.List;

public class ProductDaoImplTest {

    @Test
    public void testFindAll() {
        ProductDao productDao = new ProductDaoImpl();
        List<Product> list = productDao.findAll();
        for (Product c : list) {
            System.out.println(c.getPname());
        }
    }

    @Test
    public void testFindByPname() {
        ProductDao productDao = new ProductDaoImpl();
        Product product = productDao.findByPname("白菜");
        //System.out.println(product.getPid());
        ProductService productService = new ProductServiceImpl();
        System.out.println(productService.save(product));
    }


}
