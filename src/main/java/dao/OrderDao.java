package dao;

import domain.Order;
import domain.OrderItem;

import java.util.List;

public interface OrderDao {
    void save(Order order);

    void saveItem(OrderItem item);

    int findTotalNumByUid(String uid);

    List<Order> findByUid(String uid, int startIndex, int pagesize);

    Order findByOid(String oid);

    void changeOrderStatus(String oid, String state, String address, String name, String telephone);
}
