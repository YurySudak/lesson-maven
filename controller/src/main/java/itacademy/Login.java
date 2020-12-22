package itacademy;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        if (user.equals("admin") && pass.equals("admin")) {
            resp.addCookie(new Cookie("user", "admin"));
            resp.sendRedirect("/admin");
        } else resp.sendRedirect("/auth");
    }
}
