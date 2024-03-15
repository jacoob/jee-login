package mahdi.learning.jee.loginwebprofile.filter;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.bl.UserScopeService;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;
import mahdi.learning.jee.loginwebprofile.entity.UserScope;

import java.io.IOException;
import java.util.List;

//@WebFilter("/*")
public class LoginFilter implements Filter {

    @Inject
    private UserScopeService userScopeService;

    private ServletContext servletContext;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String path = httpServletRequest.getServletPath();

        if(path.equals("/login") || path.equals("login.jsp") || path.equals("/signup") || path.equals("signup.jsp")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        UserDto userDto = (UserDto) httpServletRequest.getSession().getAttribute("user");
        if(userDto!=null){
            //todo: check the scope of user
            //load user scope
            List<UserScope> userScopeList = userScopeService.getScopesByUserId(userDto.getUserId());
            //get the scope of method that user call it

            //check the access of user to this scope

            filterChain.doFilter(servletRequest,servletResponse);
        }else {
//            httpServletResponse.sendRedirect("login.jsp");// create loop to access /*
            httpServletRequest.getRequestDispatcher("login.jsp").forward(servletRequest,servletResponse);
        }

    }

    private boolean checkScope(HttpServletRequest request){
        String servletPath = request.getPathInfo();

        return false;

    }
}
