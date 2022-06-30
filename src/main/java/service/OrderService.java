package service;

import domain.Order;
import domain.PageBean;

public interface OrderService {
    void save(Order order);
    PageBean<Order> findByUid(String uid, int curpage, int pagesize);

    Order findByOid(String oid);

    void changeOrderStatus(String oid, String state, String address, String name, String telephone);
}
