package dao;

import domain.Category;
import domain.Product;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
    List<Product> findByHot();
    List<Product> findByNew();
    Product findBypid(String pid);
}
