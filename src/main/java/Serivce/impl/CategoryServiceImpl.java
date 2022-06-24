package Serivce.impl;

import Dao.CategoryDao;
import Dao.Impl.CategoryDaoImpl;
import Serivce.CategoryService;
import domain.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    /**
     * 用于保存后台新增类别
     * @param category
     */
    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    /**
     * 删除类别
     * @param cname
     */
    @Override
    public boolean delete(String cname) {
        return categoryDao.delete(cname);
    }
}
