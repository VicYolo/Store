package Serivce;

import domain.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAll();
    public boolean save(Category category);
    public boolean delete(String cname);
    public boolean edit(String oldCname,String newCname);
}
