package mahdi.learning.jee.loginwebprofile.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.bl.SignUpService;
import mahdi.learning.jee.loginwebprofile.dto.ProfileDto;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;

import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignupServlet extends HttpServlet {
    @Inject
    private SignUpService signUpService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileDto profileDto = ProfileDto.builder()
                .name(req.getParameter("name"))
                .lastname(req.getParameter("lastname"))
                .id(Long.parseLong(req.getParameter("id")))
                .birthday(req.getParameter("birthday"))
                .build();
        UserDto userDto = UserDto.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .email(req.getParameter("email"))
                .build();

        try {
            signUpService.add(profileDto,userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
