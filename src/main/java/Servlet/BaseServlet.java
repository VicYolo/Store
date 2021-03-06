package Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String methodName = requestURI.substring(requestURI.lastIndexOf('/') + 1);

        System.out.println(methodName);
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void writeBackInfoJson(HttpServletResponse resp, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //String json = mapper.writeValueAsString(object);
        resp.setContentType("application/json;charset=utf-8");
        //System.out.println(json);
        //resp.getWriter().write(json);
        mapper.writeValue(resp.getOutputStream(),object);
    }
}