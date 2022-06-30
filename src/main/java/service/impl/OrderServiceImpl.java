package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import domain.Order;
import domain.OrderItem;
import domain.PageBean;
import service.OrderService;
import util.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public void save(Order order) {
//        保存订单和订单项
//       事务处理 要么都成功 要么都失败
        try {
            JDBCUtils.startTransaction();
            orderDao.save(order);
            List<OrderItem> items = order.getItems();
            for (OrderItem item : items) {
                orderDao.saveItem(item);
            }
            JDBCUtils.commitAndClose();
        } catch (SQLException e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndClose();
        }
    }

    @Override
    public PageBean<Order> findByUid(String uid, int curpage, int pagesize) {
        PageBean<Order> res = new PageBean<>();
        res.setPageNumber(curpage);
        res.setPageSize(pagesize);
        res.setStartIndex((curpage-1)*pagesize);
        int totalcount = orderDao.findTotalNumByUid(uid);
        res.setTotalRecord(totalcount);
        int pagenum = totalcount/pagesize==0?(totalcount/pagesize):(totalcount/pagesize+1);
        res.setTotalPage(pagenum);
        List<Order> orders = orderDao.findByUid(uid,res.getStartIndex(),pagesize);
        res.setData(orders);
        return res;

    }

    @Override
    public Order findByOid(String oid) {
        return orderDao.findByOid(oid);
    }

    @Override
    public void changeOrderStatus(String oid, String state, String address, String name, String telephone) {
        orderDao.changeOrderStatus(oid,state,address,name,telephone);
    }

}
