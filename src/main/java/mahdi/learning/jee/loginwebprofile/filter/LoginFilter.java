package mahdi.learning.jee.loginwebprofile.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        System.out.println("Request URI:" + httpServletRequest.getRequestURI()); //      /app/
        System.out.println("Request URl:" + httpServletRequest.getRequestURL()); //       http://localhost:8080/app/
        System.out.println("Request context path:" + httpServletRequest.getContextPath());//     /app
        System.out.println("Servlet path:" + httpServletRequest.getServletPath());//     /index.jsp

        if (httpServletRequest.getSession().getId().equals(httpServletRequest.getSession().getAttribute("sessionId"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        if (httpServletRequest.getSession().getAttribute("user") != null && !httpServletRequest.getSession().getAttribute("user").equals(null)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        if (httpServletRequest.getServletPath().equals("/login") || httpServletRequest.getServletPath().equals("login.jsp") || httpServletRequest.getServletPath().equals("/signup") || httpServletRequest.getServletPath().equals("signup.jsp"))
            filterChain.doFilter(servletRequest, servletResponse);

        httpServletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
    }
}
