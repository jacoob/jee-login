package mahdi.learning.jee.loginwebprofile.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.controller.IC;
import mahdi.learning.jee.loginwebprofile.controller.MyBaseResponse;
import mahdi.learning.jee.loginwebprofile.dto.Dto;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "disp" , value = "/*")
public class MyDispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Class<?>> map = (Map<String, Class<?>>) req.getServletContext().getAttribute("map");
        String servletPath = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/"));
        if(map.get(servletPath) == null ){
            resp.getWriter().println("nist");
            return;
        }

        try {
            Object obj = map.get(servletPath).getDeclaredConstructor().newInstance();
            if(obj instanceof IC){
                MyBaseResponse myBaseResponse = ((IC) obj).service(new Dto());
                resp.getWriter().println(myBaseResponse.getData());

            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
