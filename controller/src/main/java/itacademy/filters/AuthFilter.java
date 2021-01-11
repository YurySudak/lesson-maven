package itacademy.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (response.isCommitted()) {
            chain.doFilter(req, resp);
            return;
        }
        if (request.getRequestURI().contains("/auth")) {
            chain.doFilter(req, resp);
            return;
        }
        if (request.getRequestURI().contains("/login")) {
            chain.doFilter(req, resp);
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("user"))
                    if (request.getRequestURI().contains(cookie.getValue())) {
                        chain.doFilter(req, resp);
                        return;
                    }
        response.sendRedirect("auth");
    }

    @Override
    public void destroy() {

    }
}
