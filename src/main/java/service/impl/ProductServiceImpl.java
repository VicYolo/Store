package service.impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.Order;
import domain.PageBean;
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

    @Override
    public PageBean<Product> findByCid(String cid,int curpage, int pagesize) {
        PageBean<Product> res = new PageBean<>();
        res.setPageNumber(curpage);
        res.setPageSize(pagesize);
        res.setStartIndex((curpage-1)*pagesize);
        int totalcount = categoryDao.findTotalNumByCid(cid);
        res.setTotalRecord(totalcount);
        int totalpage = totalcount/pagesize==0?(totalcount/pagesize):(totalcount/pagesize+1);
        res.setTotalPage(totalpage);
        List<Product> products = categoryDao.findByCid(cid,res.getStartIndex(),pagesize);
        res.setData(products);
        return res;
    }
}
