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
        for(Category c:list){
            System.out.println(c.getCname());
        }
    }

    @Test
    public void testDelete(){
        CategoryDao categoryDao=new CategoryDaoImpl();
        categoryDao.delete("wwwwwwwwww");
    }
}
