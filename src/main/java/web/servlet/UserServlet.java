package web.servlet;

import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;
import util.BeanFactory;
import util.UuidUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    private UserService userService = (UserService) BeanFactory.getBean("UserService");

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        System.out.println("我是UserServlet中的login方法");
        Map<String, String[]> parameterMap = req.getParameterMap();
//        将数据进行封装
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User res = userService.login(user);
        ResultInfo info = new ResultInfo();
        boolean flag;
        if(res!=null){
            flag = true;
        }else{
            flag = false;
        }
        if(flag){
            info.setFlag(true);
//            创建session 并将其放进去
            req.getSession().setAttribute("user",res);
//            System.out.println("放入......");
//            System.out.println(req.getSession().getAttributeNames());
//            User a = (User) req.getSession().getAttribute("user");
//            System.out.println(a);
//            System.out.println(a.getName());
        }else{
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        this.writeBackInfoJson(resp,info);
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        //        获取数据
        Map<String, String[]> parameterMap = req.getParameterMap();
//        将数据转化为User
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
            user.setUid(UuidUtil.getUuid());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag = userService.register(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else{
            info.setFlag(true);
            info.setErrorMsg("该用户已存在");
        }
        this.writeBackInfoJson(resp,info);
        System.out.println(info.isFlag());
        System.out.println(info.getErrorMsg());
    }

    public void getName(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        User user = (User) req.getSession().getAttribute("user");
        this.writeBackInfoJson(resp,user);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        req.getSession().invalidate();
    }
}
