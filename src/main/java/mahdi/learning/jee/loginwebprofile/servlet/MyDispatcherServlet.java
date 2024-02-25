package mahdi.learning.jee.loginwebprofile.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.Listener.MyScanner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "disp", value = "/*")
public class MyDispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Class<?>> map = (Map<String, Class<?>>) req.getServletContext().getAttribute("map");
        String servletPath = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/"));
        try {
            Class.forName(map.get(servletPath).getName());
            Object obj = map.get(servletPath).getDeclaredConstructor().newInstance();
//            if(obj instanceof map.get(servletPath))
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
