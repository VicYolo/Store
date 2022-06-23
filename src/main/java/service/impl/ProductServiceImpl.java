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
}
