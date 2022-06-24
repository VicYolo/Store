package Dao.Impl;

import Dao.CategoryDao;
import domain.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from category";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }

    /**
     * 用于保存后台添加类别
     *
     * @param category
     */
    @Override
    public void save(Category category) {
        String sql = "insert into category values (?,?)";
        template.update(sql, category.getCid(), category.getCname());
    }

    /**
     * 删除类别
     *
     * @param cname
     */
    @Override
    public boolean delete(String cname) {
        String sql = "delete from category where cname = ?";
        try{
            template.update(sql, cname);
            System.out.println(cname + "从数据库删除成功");
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
