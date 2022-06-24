package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import domain.Order;
import domain.OrderItem;
import org.springframework.jdbc.datasource.DataSourceUtils;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
    public void save(Order order) throws Exception{
        try{
            OrderDao dao=new OrderDaoImpl();
            //获取dao
//            OrderDao od=
            //开启事务
//            DataSourceUtils.startTransaction();
            //向orders表中插入一条
            dao.save(order);
            //向order item表中插入
            for(OrderItem oi:order.getItems()){
                dao.saveItem(oi);
            }
            //事务控制
//            DataSourceUtils.commitAndClose();
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
