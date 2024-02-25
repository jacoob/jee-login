package mahdi.learning.jee.loginwebprofile.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mahdi.learning.jee.loginwebprofile.bl.ScopeService;
import mahdi.learning.jee.loginwebprofile.dto.ScopeDto;
import mahdi.learning.jee.loginwebprofile.entity.Users;

import java.io.IOException;

@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {

    @Inject
    private ScopeService scopeService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScopeDto dto = ScopeDto.builder().name(req.getParameter("scope")).user(Users.builder().username(req.getParameter(req.getParameter("username"))).build()).build();
        scopeService.findScopeByUserName(dto);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScopeDto dto = ScopeDto.builder().name(req.getParameter("scope")).user(Users.builder().username(req.getParameter(req.getParameter("username"))).build()).build();
        scopeService.addScope(dto);
    }
}
