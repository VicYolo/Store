package Dao;

import domain.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAll();
    public void save(Category category);
    public boolean delete(String cname);
}
