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

@WebServlet(value = "/password/recovery")
public class RecoverPasswordServlet extends HttpServlet {
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = userService.getUserByEmailUsername(UserDto.builder().username(req.getParameter("username")).email(req.getParameter("email")).build());
//        resp.getWriter().println("username : "+userDto.getUsername() + ", password "+ userDto.getPassword());
        req.setAttribute("msg", userDto.getPassword());
        req.getRequestDispatcher("general1.jsp").forward(req, resp);
    }
}
