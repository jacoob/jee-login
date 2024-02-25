package mahdi.learning.jee.loginwebprofile.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
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
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
//            httpServletResponse.sendRedirect("login.jsp");// create loop to access /*
            httpServletRequest.getRequestDispatcher("login.jsp").forward(servletRequest,servletResponse);
        }

    }
}
