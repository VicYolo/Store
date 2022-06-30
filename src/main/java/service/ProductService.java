package service;

import domain.PageBean;
import domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findByHot();
    List<Product> findByNew();
    Product findBypid(String pid);
    PageBean<Product> findByCid(String cid,int curpage, int pagesize);
}
