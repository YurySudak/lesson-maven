package itacademy;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Cookie[] cookies = request.getCookies();
        boolean isCookie = false;
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("user") && cookie.getValue().equals("admin"))
                    isCookie = true;
        if (isCookie)
            chain.doFilter(req, resp);
        else
            response.sendRedirect("/auth");
    }

    @Override
    public void destroy() {

    }
}
