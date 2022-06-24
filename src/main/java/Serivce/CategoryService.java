package Serivce;

import domain.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();
    public void save(Category category);
    public boolean delete(String cname);
}
