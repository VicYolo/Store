import Dao.Impl.ProductDaoImpl;
import Dao.ProductDao;
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
}
