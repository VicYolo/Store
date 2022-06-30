import Dao.CategoryDao;
import Dao.Impl.CategoryDaoImpl;
import domain.Category;
import org.junit.Test;

import java.util.List;

public class CategoryDaoImplTest {

    @Test
    public void testFindAll() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list = categoryDao.findAll();
        for (Category c : list) {
            System.out.println(c.getCname());
        }
    }

    @Test
    public void testDelete() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.delete("wwwwwwwwww");
    }

    @Test
    public void testEdit() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        Category category = categoryDao.findByCname("汽车用品");
        category.setCname("汽车小用品");
        categoryDao.edit(category);
    }
}
