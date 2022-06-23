package web.servlet;

import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;
import util.UuidUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("我是UserServlet中的login方法");
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
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
//        UserService userService = new UserServiceImpl();
        boolean flag = userService.login(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        this.writeBackInfoJson(resp,info);
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

}
