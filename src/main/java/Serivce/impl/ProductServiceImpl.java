package Serivce.impl;

import Dao.CategoryDao;
import Dao.Impl.CategoryDaoImpl;
import Dao.Impl.ProductDaoImpl;
import Dao.ProductDao;
import Serivce.ProductService;
import domain.Category;
import domain.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public boolean save(Product product) {
        //如果没有重复
        if (productDao.findByPname(product.getPname()) == null) {
            productDao.save(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void delete(String pname) {
        productDao.delete(pname);
    }

    @Override
    public boolean edit(Product product, boolean flag) {
        //如果没有重复
        Product oldproduct = productDao.findByPname(product.getPname());
        if (oldproduct == null || oldproduct.getPid().equals(product.getPid())) {
            productDao.edit(product, flag);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product findByPname(String pname) {
        Product product = productDao.findByPname(pname);
        Category category = categoryDao.findByCid(product.getCategory().getCid());
        product.setCategory(category);
        return product;
    }
}
