package Dao;

import domain.Category;
import domain.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> findAll();

    public void save(Product product);

    public void delete(String pname);

    public void edit(Product product);

    public Product findByPname(String cname);
}
