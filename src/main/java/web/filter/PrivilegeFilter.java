package web.filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrivilegeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp  = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");
        System.out.println("正在拦截");
        if(user==null){
//            不知道为什么不能转发或重定向
            req.getRequestDispatcher(req.getContextPath()+"/login.html").forward(req,resp);
            System.out.println("拦截成功");
        }else{
            filterChain.doFilter(req,resp);
            System.out.println("我在哪");
        }

    }

    @Override
    public void destroy() {

    }
}
