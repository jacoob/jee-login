package mahdi.learning.jee.loginwebprofile.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.bl.ProfileService;
import mahdi.learning.jee.loginwebprofile.dto.ProfileDto;

import java.io.IOException;

@WebServlet(name = "profile", value = "/profile")
public class ProfileServlet extends HttpServlet {

    @Inject
    private ProfileService profileService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileDto profileDto = ProfileDto.builder()
                .name(req.getParameter("name"))
                .lastname(req.getParameter("lastname"))
                .id(Long.parseLong(req.getParameter("id")))
                .email(req.getParameter("email"))
                .birthday(req.getParameter("birthday"))
                .build();

        profileService.addProfile(profileDto);

    }
}
