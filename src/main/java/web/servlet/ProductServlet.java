package web.servlet;

import domain.PageBean;
import domain.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/*")
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
    public void findByCid(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String cid = req.getParameter("cid");
        int curpage = 1;
        try{
            curpage = Integer.parseInt(req.getParameter("curpage"));
        }catch (Exception e){
        }
        int pagesize = 12; //默认一个界面显示12个商品
        PageBean<Product> pageBean = productService.findByCid(cid, curpage, pagesize);
        writeBackInfoJson(resp,pageBean);
    }

}
