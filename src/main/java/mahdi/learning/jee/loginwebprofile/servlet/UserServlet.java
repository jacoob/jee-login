package mahdi.learning.jee.loginwebprofile.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.bl.UserService;
import mahdi.learning.jee.loginwebprofile.dto.ProfileDto;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;

import java.io.IOException;

//@ApplicationScoped
@WebServlet(name = "users" , value = "/user")
public class UserServlet extends HttpServlet {
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email =   req.getParameter("email");

        userService.addUser(
                UserDto
                .builder()
                        .username(username)
                        .password(password)
                        .profileDto(ProfileDto.builder()
                                .email(email).build())
                .build());
    }
}
