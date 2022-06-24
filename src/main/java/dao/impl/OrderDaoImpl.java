package dao.impl;

import dao.OrderDao;
import domain.Order;
import domain.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderDaoImpl implements OrderDao {
    //保存订单
    public void save(Order order) throws Exception{
        JdbcTemplate template=new JdbcTemplate();
        String sql="insert into orders values(?,?,?,?,?,?,?,?);";
        template.update(sql,order.getOid(),order.getOrdertime(),
                order.getTotal(),order.getState(),order.getAddress(),
                order.getName(),order.getTelephone(),order.getUser().getUid());
    }
    //保存订单项
    public void saveItem(OrderItem oi) throws Exception{
        JdbcTemplate template=new JdbcTemplate();
        String sql="insert into orders values(?,?,?,?);";
        template.update(sql,oi.getItemid(),oi.getCount(),oi.getSubtotal(),
                oi.getProduct().getPid(),oi.getOrder().getOid());
    }
}
