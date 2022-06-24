package Servlet;

import Serivce.ProductService;
import Serivce.impl.ProductServiceImpl;
import domain.Category;
import domain.Product;
import domain.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet(value = "/adminProductServlet/*")
public class AdminProductServlet extends BaseServlet {

    ProductService productService = new ProductServiceImpl();

    /**
     * 查询并展示后台商品
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1调用service获取所有分类
        List<Product> list = productService.findAll();
        //2写成json返回
        writeBackInfoJson(response, list);
    }

    /**
     * 后台删除商品
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        request.setCharacterEncoding("utf-8");
        String pname = request.getParameter("pname");
        System.out.println("解码前：" + pname);
        pname = URLDecoder.decode(pname, "utf-8");
        System.out.println("解码后：" + pname);
        //3调用service删除
        productService.delete(pname);

    }

}
