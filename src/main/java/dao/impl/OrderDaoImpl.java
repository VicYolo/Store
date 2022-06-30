package dao.impl;

import dao.OrderDao;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void save(Order order) {
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
                            order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());

    }

    @Override
    public void saveItem(OrderItem item) {
        String sql = "insert into orderitem values(?,?,?,?,?)";
        jdbcTemplate.update(sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid());

    }

    @Override
    public int findTotalNumByUid(String uid) {
        System.out.println(uid);
        String sql = "select count(*) from orders where uid = ?";
//        String sql = "select count(*) from orders where uid = '"+uid+"'";
        int r = 0;
        try{
            r = jdbcTemplate.queryForObject(sql,new Object[]{uid},Integer.class);
        }catch(Exception e){
            System.out.println("出错了");
        }
        System.out.println(r);
        return r;
    }

    @Override
    public List<Order> findByUid(String uid, int startIndex, int pagesize) {
        String sql = "select * from orders where uid = ? order by ordertime desc limit ?,?";
        List<Order> orders = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order>(Order.class), uid,startIndex,pagesize);
        for (Order order : orders) {
            String subsql = "select * from orderitem where oid = ?";
            List<OrderItem> orderItems = jdbcTemplate.query(subsql, new BeanPropertyRowMapper<OrderItem>(OrderItem.class), order.getOid());
            for (OrderItem orderItem : orderItems) {
                String subsql2 = "select * from product where pid in " +
                                    "(select pid from orderitem where itemid = ?)";
                Product product = jdbcTemplate.queryForObject(subsql2, new BeanPropertyRowMapper<Product>(Product.class),orderItem.getItemid());
                orderItem.setProduct(product);
            }
            order.setItems(orderItems);
        }
        return orders;
    }

    @Override
    public Order findByOid(String oid) {
        String sql = "select * from orders where oid = ?";
        Order order = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Order>(Order.class),oid);
        String subsql = "select * from orderitem where oid = ?";
        List<OrderItem> orderItems = jdbcTemplate.query(subsql, new BeanPropertyRowMapper<OrderItem>(OrderItem.class), oid);
        for (OrderItem orderItem : orderItems) {
            String subsql2 = "select * from product where pid in " +
                    "(select pid from orderitem where itemid = ?)";
            Product product = jdbcTemplate.queryForObject(subsql2, new BeanPropertyRowMapper<Product>(Product.class),orderItem.getItemid());
            orderItem.setProduct(product);
        }
        order.setItems(orderItems);
        return order;
    }

    @Override
    public void changeOrderStatus(String oid, String state, String address, String name, String telephone) {
        String sql = "update orders set state = ?,address = ?,name = ?, telephone= ?  where oid = ?";
        jdbcTemplate.update(sql,state,address,name,telephone,oid);
    }

}
