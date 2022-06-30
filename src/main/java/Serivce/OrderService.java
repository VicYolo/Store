package Serivce;


import domain.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();

    public boolean edit(String oid);

    public Order findByOid(String oid);
}
