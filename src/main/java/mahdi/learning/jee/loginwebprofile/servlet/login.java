package mahdi.learning.jee.loginwebprofile.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.bl.UserService;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginservlet",value = "/login")
public class login extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = UserDto.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .build();
        UserDto userDto1 = userService.getUserByUsernamePassword(userDto);
        if(userDto1!=null && !userDto1.equals(null)){
            req.getSession().setAttribute("sessionId",req.getSession().getId());
            req.getSession().setAttribute("user",userDto);
        }

        PrintWriter out = resp.getWriter();
        out.println("user" + userDto1.getUsername() + " is logged in" + userDto1.getTxtSecurityKey());
    }
}
