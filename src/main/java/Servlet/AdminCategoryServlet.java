package Servlet;

import Serivce.CategoryService;
import Serivce.impl.CategoryServiceImpl;
import domain.Category;
import domain.ResultInfo;
import util.UuidUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@WebServlet(value = "/adminCategoryServlet/*")
public class AdminCategoryServlet extends BaseServlet {

    CategoryService categoryService = new CategoryServiceImpl();

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
        List<Category> list = categoryService.findAll();
        //写成json返回
        writeBackInfoJson(response, list);
        //2返回值放到request域中
        //request.setAttribute("list", list);
        //3请求转发
        //request.getRequestDispatcher("/admin_html/category/list.html").forward(request, response);
        //return;
    }

    /**
     * 保存后台新增类别
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        String cname = request.getParameter("category");
        //2封装category
        Category category = new Category();
        category.setCid(UuidUtil.getUuid());
        category.setCname(cname);
        //3调用service保存
        boolean flag = categoryService.save(category);
        System.out.println(flag);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        if (!flag) {
            resultInfo.setErrorMsg("添加失败，输入名称有重复");
        }
        writeBackInfoJson(response, resultInfo);
    }

    /**
     * 后台删除类别
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        request.setCharacterEncoding("utf-8");
        String cname = request.getParameter("cname");
        System.out.println("解码前：" + cname);
        cname = URLDecoder.decode(cname, "utf-8");
        System.out.println("解码后：" + cname);
        //3调用service删除
        boolean flag = categoryService.delete(cname);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        if (!flag) {
            resultInfo.setErrorMsg("删除失败，该分类下面还有商品");
        }

        writeBackInfoJson(response, resultInfo);
    }


    /**
     * 后台编辑类别
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取数据内容
        request.setCharacterEncoding("utf-8");
        String oldCname = request.getParameter("oldCname");
        String newCname = request.getParameter("newCname");

        //2解码
        System.out.println("解码前：" + oldCname + "  " + newCname);
        oldCname = URLDecoder.decode(oldCname, "utf-8");
        oldCname = URLDecoder.decode(oldCname, "utf-8");//解两次码
        newCname = URLDecoder.decode(newCname, "utf-8");
        System.out.println("解码后：" + oldCname + "  " + newCname);

        //3调用service编辑
        boolean flag = categoryService.edit(oldCname, newCname);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        if (!flag) {
            resultInfo.setErrorMsg("编辑失败，输入名称有重复");
        }
        writeBackInfoJson(response, resultInfo);
    }

}
