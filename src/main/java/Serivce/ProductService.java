package Serivce;

import domain.Category;
import domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public boolean save(Product product);

    public void delete(String cname);

    public boolean edit(Product product, boolean flag);

    public Product findByPname(String pname);
}
