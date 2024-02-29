package mahdi.learning.jee.loginwebprofile.framwork;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebListener
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
        List<Class<?>> classes = new ArrayList<>();
        try {
            List<Class<?>> classList = MyScanner.getClasses("mahdi.learning.jee.loginwebprofile");
            classes = classList.stream().filter(MyScanner::checkForCustomAnnotation).collect(Collectors.toList());
            Map<String, Class<?>> map = MyScanner.getMapClassPath(classes);
            sce.getServletContext().setAttribute("map", map);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
