package storage.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if(req.getRequestURI().contains("resources")) {
            filterChain.doFilter(req, resp);
            return;
        }
        HttpSession session = req.getSession(false);
        boolean isAuthenticated = false;
        boolean sessionExists = session != null;
        boolean isRequestOnAuthPage = req.getRequestURI().contains("sign-in") ||
                req.getRequestURI().contains("sign-up");

        if (sessionExists) {
            isAuthenticated = session.getAttribute("user") != null;
        }

        if (isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage) {
            filterChain.doFilter(req, resp);
        } else if (isAuthenticated && isRequestOnAuthPage) {
            resp.sendRedirect("profile");
        } else {
            resp.sendRedirect("sign-in");
        }
    }

    @Override
    public void destroy() {}
}
