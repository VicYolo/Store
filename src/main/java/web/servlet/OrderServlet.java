package web.servlet;

import domain.*;
import service.OrderService;
import service.impl.OrderServiceImpl;
import util.UuidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();

    public void save(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        获取用户的购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
//        将购物车封装为订单
        Order order = new Order();
        order.setOid(UuidUtil.getUuid());
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UuidUtil.getUuid());
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        }
        order.setOrdertime(new Date());
        order.setTotal(cart.getTotal());
        order.setUser(user);
        order.setState(0);
//        将订单保存到数据库
        orderService.save(order);
//        清除购物车
        cart.clearCart();
        req.getSession().setAttribute("cart",cart);
        req.getSession().setAttribute("order",order);

    }

    public void showCurOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Order order = (Order) req.getSession().getAttribute("order");
//        System.out.println(order.getItems().size());
        this.writeBackInfoJson(resp,order);
    }

    public void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException{
//        获取uid
        User user = (User) req.getSession().getAttribute("user");
        int curpage = 1;
        try{
            curpage = Integer.parseInt(req.getParameter("curpage"));
        }catch (Exception e){
        }
        int pagesize = 3; //默认一个界面显示3个订单
        PageBean<Order> pageBean = orderService.findByUid(user.getUid(),curpage,pagesize);
        writeBackInfoJson(resp,pageBean);
    }

    public void getOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String oid = req.getParameter("oid");
        Order order = orderService.findByOid(oid);
        writeBackInfoJson(resp,order);
    }
    public void changeOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String state = req.getParameter("orderState");
        String oid = req.getParameter("oid");
        String address = req.getParameter("address");
        String name = req.getParameter("name");
        String telephone = req.getParameter("telephone");
        orderService.changeOrderStatus(oid,state,address,name,telephone);
    }

}
