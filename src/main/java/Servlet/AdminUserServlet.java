package Servlet;

import Serivce.ProductService;
import Serivce.UserService;
import Serivce.impl.ProductServiceImpl;
import Serivce.impl.UserServiceImpl;
import domain.Product;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet(value = "/adminUserServlet/*")
public class AdminUserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    /**
     * 查询并展示所有用户
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1调用service获取所有分类
        List<User> list = userService.findAll();
        //2写成json返回
        writeBackInfoJson(response, list);
    }

    /**
     * 后台删除用户
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        System.out.println("解码前：" + username);
        username = URLDecoder.decode(username, "utf-8");
        System.out.println("解码后：" + username);
        //3调用service删除
        userService.delete(username);
    }

    /**
     * 后台查询用户详情
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("uname");
        System.out.println("解码前：" + username);
        username = URLDecoder.decode(username, "utf-8");
        System.out.println("解码后：" + username);
        //3调用service查询
        User user = userService.adminFindByUsername(username);

        writeBackInfoJson(response, user);
    }

}
