package storage.servlets;

import storage.dto.SignUpForm;
import storage.dto.UserDto;
import storage.exceptions.StorageEx;
import storage.services.Authorization;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/sign-up")
public class SignUp extends HttpServlet {
    private Authorization authorization;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        authorization = (Authorization) servletContext.getAttribute("authorization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign-up.ftl").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        SignUpForm form = SignUpForm.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .birthdate(Date.valueOf(req.getParameter("birthdate")))
                .build();
        UserDto user;
        try {
            user = authorization.signUp(form);
        } catch (StorageEx e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("sign-up.ftl").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect("profile");
    }
}
