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
     *
     * @param category
     */
    @Override
    public boolean save(Category category) {
        //如果没有重复
        if (categoryDao.findByCname(category.getCname()) == null) {
            categoryDao.save(category);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除类别
     *
     * @param cname
     */
    @Override
    public boolean delete(String cname) {
        return categoryDao.delete(cname);
    }

    @Override
    public boolean edit(String oldCname, String newCname) {
        //判断新名称是否重名
        if (categoryDao.findByCname(newCname) == null) {
            Category newcategory = categoryDao.findByCname(oldCname);
            newcategory.setCname(newCname);
            categoryDao.edit(newcategory);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Category findByCname(String cname) {
        return categoryDao.findByCname(cname);
    }


}
