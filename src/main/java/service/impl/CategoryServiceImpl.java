package service.impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.Category;
import service.CategoryService;
import util.BeanFactory;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    //    CategoryDao categoryDao = new CategoryDaoImpl();
    CategoryDao categoryDao = (CategoryDao) BeanFactory.getBean("CategoryDao");

    @Override
    public List<Category> findAll() {
//        缓存部分遇到一丢丢麻烦
//        Jedis jedis = JedisUtil.getJedis();
//
//        Set<String> categorys = jedis.zrange("category",0,-1);
//        List<Category> res;
//        if(categorys==null || categorys.size()==0){
//            System.out.println("从数据库中取数据");
//            res = categoryDao.findAll();
//            //                将每个数据转化为字符串
//            for (Category re : res) {
//
//            }
//        }else{
//            System.out.println("从redis中查询");
//            res = new ArrayList<>();
//            for(String )
//        }
        return categoryDao.findAll();
    }
}
