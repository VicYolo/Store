package web.servlet;

import domain.Cart;
import domain.CartItem;
import domain.Product;
import domain.ResultInfo;
import service.ProductService;
import util.BeanFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet{

    ProductService productService = (ProductService) BeanFactory.getBean("ProductService");
    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println(req.getParameter("count"));
        Integer count = Integer.parseInt(req.getParameter("count"));
        String pid = req.getParameter("pid");
        ResultInfo info = new ResultInfo();
        if( count!=null &&pid !=null){
//            找商品
            Product product = productService.findBypid(pid);
            if(product!=null){
//                创建购物车项
                CartItem cartItem = new CartItem(product,count);
//            根据session获取购物车
                Cart cart = (Cart) req.getSession().getAttribute("cart");
                if(cart==null){
                    cart = new Cart();
                }
                cart.add2cart(cartItem);
                req.getSession().setAttribute("cart",cart);
                info.setFlag(true);
            }else{
                info.setFlag(false);
                info.setErrorMsg("添加失败");
            }

        }else{
            info.setFlag(false);
            info.setErrorMsg("添加失败");
        }
        writeBackInfoJson(resp,info);
    }

    public void getCart(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        this.writeBackInfoJson(resp,cart);
    }
    public void clearCart(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearCart();
        req.getSession().setAttribute("cart",cart);
    }
    public void deleteGood(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        String pid = req.getParameter("pid");
        cart.removeFromCart(pid);
        req.getSession().setAttribute("cart",cart);
    }
}
