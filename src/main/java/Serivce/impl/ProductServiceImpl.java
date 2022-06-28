package Serivce.impl;

import Dao.Impl.ProductDaoImpl;
import Dao.ProductDao;
import Serivce.ProductService;
import domain.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();

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
    public boolean edit(String oldCname, String newCname) {
        return false;
    }
}
