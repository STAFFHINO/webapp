package storage.servlets;

import storage.dto.SignInForm;
import storage.dto.UserDto;
import storage.exceptions.StorageEx;
import storage.services.Authorization;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignIn extends HttpServlet {
    Authorization authorization;

    @Override
    public void init(ServletConfig config) throws ServletException {
        authorization = (Authorization) config.getServletContext().getAttribute("authorization");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign-in.ftl").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SignInForm form = SignInForm.builder()
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .build();
            UserDto user = authorization.signIn(form);
            req.getSession(true).setAttribute("user", user);
            resp.sendRedirect("profile");
        } catch (StorageEx e){
            req.setAttribute("errorMessage", e.getMessage());
            System.out.println(e.getMessage());
            req.getRequestDispatcher("sign-in.ftl").forward(req,resp);
        }
    }
}
