import Dao.Impl.OrderDaoImpl;
import Dao.OrderDao;
import domain.Order;
import domain.OrderItem;
import org.junit.Test;

import java.util.List;

public class OrderDaoImplTest {

    @Test
    public void testFindByOid() {
        OrderDao orderDao = new OrderDaoImpl();
        Order order = orderDao.findByOid("123");
        System.out.println(order.getOid());
        System.out.println(order.getOrdertime());
        List<OrderItem> list = order.getItems();
        for (OrderItem orderItem : list) {
            System.out.println(orderItem.getProduct().getPname());
            System.out.println(orderItem.getItemid() + "  " + orderItem.getCount());
        }
        System.out.println(order.getTotal());
    }
}
