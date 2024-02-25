package mahdi.learning.jee.loginwebprofile.Listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static mahdi.learning.jee.loginwebprofile.Listener.MyScanner.getMapClassPath;

@WebListener
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            List<Class<?>> classes = new ArrayList<>();
            List<Class<?>> classList = MyScanner.getClasses("mahdi.learning.jee.loginwebprofile.controller");
           classes = classList.stream().filter(MyScanner::checkForCustomAnnotation).collect(Collectors.toList());
          Map<String,Class<?>> map =  MyScanner.getMapClassPath(classes);
            sce.getServletContext().setAttribute("map" , map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
