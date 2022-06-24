package service.impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Product> findByHot() {
        return categoryDao.findByHot();
    }

    @Override
    public List<Product> findByNew() {
        return categoryDao.findByNew();
    }

    @Override
    public Product findBypid(String pid) {
        Product bypid = categoryDao.findBypid(pid);
//        System.out.println(bypid);
        return bypid;
    }
}
