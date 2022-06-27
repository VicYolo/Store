package web.servlet;

import domain.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/findBypid")
public class ProductServlet extends BaseServlet{
    private ProductService productService = new ProductServiceImpl();
    public void findBypid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pid = req.getParameter("pid");
        if("null".equals(pid)){
            pid = null;
        }
//        System.out.println(pid);
        Product product = productService.findBypid(pid);
        writeBackInfoJson(resp,product);
    }
}
