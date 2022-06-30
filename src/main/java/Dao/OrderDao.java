package Dao;

import domain.Order;

import java.util.List;

public interface OrderDao {
    public List<Order> findAll();

    public void edit(String oid);

    public Order findByOid(String oid);
}
