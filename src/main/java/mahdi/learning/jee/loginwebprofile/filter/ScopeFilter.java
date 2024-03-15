package mahdi.learning.jee.loginwebprofile.filter;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.bl.ScopeService;
import mahdi.learning.jee.loginwebprofile.bl.UserScopeService;
import mahdi.learning.jee.loginwebprofile.dto.ScopeDto;
import mahdi.learning.jee.loginwebprofile.scope.Scope;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter("/secure/*")
public class ScopeFilter implements Filter {

    @Inject
    private ScopeService scopeService;

    @Inject
    private UserScopeService userScopeService;

    private ServletContext servletContext;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the requested URL
        String urlPattern = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        //////
        Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
        for (ServletRegistration registration : servletRegistrations.values()) {
            System.out.println("Servlet Name: " + registration.getName());
            System.out.println("Servlet Class: " + registration.getClassName());
            System.out.println("Mappings: " + registration.getMappings());
            // You can print other relevant information here
        }

        List<? extends ServletRegistration> list = servletRegistrations.entrySet().stream().filter(p -> p.getValue().getMappings().contains(urlPattern)).map(Map.Entry::getValue).toList();
        if (list != null && !list.isEmpty()) {
//            String sco = Arrays.stream(list.get(0).getClass().getAnnotations()).filter(p -> p.annotationType().isAnnotationPresent(Scope.class)).map(p -> p.annotationType().getAnnotation(Scope.class).value()).toString();
            ServletRegistration servletRegistration = list.get(0);
            String className = servletRegistration.getClassName();
            Class clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(clazz.isAnnotationPresent(Scope.class)){
               Scope scope = (Scope) clazz.getAnnotation(Scope.class);
               String s = scope.value();
               ScopeDto scopeDto = scopeService.findScopeByName(ScopeDto.builder().name(s).build()).get(0);
               if(userScopeService.isAccessScopesUserIdToScopeId(1l,scopeDto.getId())){
                   chain.doFilter(request,response);
               }
            }


        }
        //////
        // Find the Servlet Registration for the URL pattern

        ServletRegistration servletRegistration = servletContext.getServletRegistration(urlPattern);
        if (servletRegistration != null) {
            // Get the Servlet class name
            String[] servletClassNames = servletRegistration.getMappings().toArray(new String[0]);
            if (servletClassNames.length > 0) {
                String servletClassName = servletClassNames[0];
                // Now you have the Servlet class name associated with the requested URL
                httpRequest.setAttribute("servletClassName", servletClassName);
                chain.doFilter(request, response);
                return;
            }
        }

        // If Servlet class not found for the requested URL
//        httpResponse.sendRedirect("login.jsp");
        httpRequest.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    // Other methods for Filter interface
}
