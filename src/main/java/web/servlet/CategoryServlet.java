package web.servlet;

import domain.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{
    CategoryService categoryService = new CategoryServiceImpl();
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        List<Category> res = categoryService.findAll();
//        for (Category re : res) {
//            System.out.println(re.getCname());
//        }
        this.writeBackInfoJson(resp,res);
    }
}
