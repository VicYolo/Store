package dao;

import domain.Order;
import domain.OrderItem;

public interface OrderDao {
    void save(Order order) throws Exception;
    void saveItem(OrderItem oi) throws Exception;
}
