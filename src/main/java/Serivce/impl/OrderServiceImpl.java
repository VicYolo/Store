package Serivce.impl;

import Dao.Impl.OrderDaoImpl;
import Dao.OrderDao;
import Serivce.OrderService;
import domain.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public boolean edit(String oid) {
        orderDao.edit(oid);
        return true;
    }

    @Override
    public Order findByOid(String oid) {
        return orderDao.findByOid(oid);
    }
}
