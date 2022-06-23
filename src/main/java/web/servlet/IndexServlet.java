package web.servlet;

import domain.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index/*")
public class IndexServlet extends BaseServlet{
    ProductService productService = new ProductServiceImpl();
    public void findHot(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> hot_products = productService.findByHot();
        writeBackInfoJson(resp,hot_products);
    }
    public void findNew(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> new_products = productService.findByNew();
        writeBackInfoJson(resp,new_products);
    }
}
