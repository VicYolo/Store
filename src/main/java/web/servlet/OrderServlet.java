package web.servlet;

import domain.*;
import org.springframework.beans.factory.BeanFactory;
import service.OrderService;
import service.impl.OrderServiceImpl;
import util.UuidUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.lang.String;

public class OrderServlet extends BaseServlet{
    private static final long serialVersionUID=1L;

    //保存订单
    public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        //获取user
        User user= (User) request.getSession().getAttribute("user");
        if(user==null){
            request.setAttribute("msg","未登录，请先登录！");
            return "/WEB-INF/login.html";
        }
        //获取购物车
        Cart cart=(Cart)request.getSession().getAttribute("cart");
        //封装订单对象
        //1.1创建对象
        Order order=new Order();
        //1.2设置属性
        order.setOid(UuidUtil.getUuid());
        order.setOrdertime(new Date());
        order.setTotal(cart.getTotal());
        order.setState(0);
        order.setUser(user);
        for(CartItem item:cart.getCartItems()){
            //封装成orderitem
            OrderItem ot=new OrderItem();
            ot.setItemid(UuidUtil.getUuid());
            ot.setCount(item.getCount());
            ot.setSubtotal(item.getSubtotal());
            ot.setProduct(item.getProduct());
            ot.setOrder(order);

            //将orderitem加入order中的items
            order.getItems().add(ot);
        }
        //调用orderService完成保存操作
        OrderService os=new OrderServiceImpl();
        try {
            os.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //OrderService os= (OrderService) BeanFactory.getBean("OrderService")
        //请求转发到info
        request.setAttribute("bean","order");
        return"/WEB-INF/order_info.html";
    }
}
