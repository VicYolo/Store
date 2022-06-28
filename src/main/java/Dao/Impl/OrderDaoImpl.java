package Dao.Impl;

import Dao.OrderDao;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Order> findAll() {
        String sql = "select * from orders order by state";
        return template.query(sql, new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public void edit(String oid) {
        String sql = "update orders set state = 2 where oid = ?";
        template.update(sql, oid);
    }

    @Override
    public Order findByOid(String oid) {
        String sql = "select * from orders where oid = ?";
        Order order = template.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class), oid);
        //
        String sqll = "select * from orderitem where oid = ?";
        //List<OrderItem> list = template.query(sqll, new BeanPropertyRowMapper<OrderItem>(OrderItem.class), oid);
        List<OrderItem> list = new ArrayList<>();

        //order--orderitem--product
        List<Map<String, Object>> list1 = template.queryForList(sqll, oid);
        for (Map<String, Object> map : list1) {
            OrderItem orderItem = new OrderItem();
            String sqlll = "select * from product where pid = ?";
            Product product = template.queryForObject(sqlll, new BeanPropertyRowMapper<Product>(Product.class), (String) map.get("pid"));
            orderItem.setProduct(product);
            orderItem.setItemid((String) map.get("itemid"));
            orderItem.setOrder(order);
            orderItem.setCount((Integer) map.get("count"));
            orderItem.setSubtotal((Double) map.get("subtotal"));
            list.add(orderItem);
        }
        order.setItems(list);

        return order;
    }
}
