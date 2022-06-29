package Servlet;

import Serivce.CategoryService;
import Serivce.OrderService;
import Serivce.impl.CategoryServiceImpl;
import Serivce.impl.OrderServiceImpl;
import domain.Category;
import domain.Order;
import domain.ResultInfo;
import domain.User;
import util.UuidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(value = "/adminOrderServlet/*")
public class AdminOrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    /**
     * 查询并展示后台类别
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1调用service获取所有分类
        List<Order> list = orderService.findAll();
        //2写成json返回
        writeBackInfoJson(response, list);
    }


    /**
     * 后台编辑订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        request.setCharacterEncoding("utf-8");
        String oid = request.getParameter("oid");

        //2解码
        oid = URLDecoder.decode(oid, "utf-8");//解两次码

        //3调用service编辑
        orderService.edit(oid);

    }

    /**
     * 后台初始化查询订单信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        request.setCharacterEncoding("utf-8");
        String oid = request.getParameter("oid");
        System.out.println("解码前：" + oid);
        oid = URLDecoder.decode(oid, "utf-8");
        System.out.println("解码后：" + oid);
        //3调用service查询
        Order order = orderService.findByOid(oid);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年mm月mm日 HH:mm:ss");
        //
        //System.out.println(simpleDateFormat.format(order.getOrdertime()));
        //order.setOrdertime(simpleDateFormat.format(order.getOrdertime()));

        writeBackInfoJson(response, order);

    }

}
