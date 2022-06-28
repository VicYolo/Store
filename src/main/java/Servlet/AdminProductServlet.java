package Servlet;

import Serivce.CategoryService;
import Serivce.ProductService;
import Serivce.impl.CategoryServiceImpl;
import Serivce.impl.ProductServiceImpl;
import domain.Category;
import domain.Product;
import domain.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import util.UuidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/adminProductServlet/*")
public class AdminProductServlet extends BaseServlet {

    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();

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


    /**
     * 后台添加商品
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //上传图片
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(3 * 1024 * 1024);
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        fileUpload.setHeaderEncoding("UTF-8");
        List<FileItem> list = fileUpload.parseRequest(request);
        Map<String, String> map = new HashMap<String, String>();
        String filename = null;
        String cname = null;
        int count = 0;
        for (FileItem fileItem : list) {
            if (fileItem.isFormField()) {
                //若不是图片
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                System.out.println(name + "   " + value);
                if (name.equals("cid"))
                    cname = value;
                map.put(name, value);
                count++;
            } else {
                //若是图片
                filename = fileItem.getName();
                System.out.println("文件名: " + filename);
                InputStream inputStream = fileItem.getInputStream();
                String path = this.getServletContext().getRealPath("/products/1");
                OutputStream outputStream = new FileOutputStream(path + "/" + filename);
                IOUtils.copy(inputStream, outputStream);
            }

            Product product = new Product();
            //封装数据
            BeanUtils.populate(product, map);
            product.setPid(UuidUtil.getUuid());
            product.setPdate(new Date());
            product.setPflag(0);
            product.setPimage("products/1/" + filename);

            if (count == 6) {
                //System.out.println("cname = " + cname);
                Category category = categoryService.findByCname(cname);
                product.setCategory(category);
                //System.out.println(category.getCname());
                boolean flag = productService.save(product);
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setFlag(flag);
                System.out.println("flag = " + flag);
                if (!flag) {
                    System.out.println("加入失败，输入名称有重复");
                    resultInfo.setErrorMsg("加入失败，输入名称有重复");
                }
                writeBackInfoJson(response, resultInfo);
            }
            //System.out.println(category.getCname());
            //System.out.println(category.getCid());


        }


    }

}
