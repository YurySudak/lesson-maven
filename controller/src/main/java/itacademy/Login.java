package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/login"})
public class Login extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        Passwords.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        if (Passwords.getMap().containsKey(user)) {
            if (pass.equals(Passwords.getMap().get(user))) {
                resp.addCookie(new Cookie("user", user));
                resp.sendRedirect("/" + user);
            } else out(resp);
        } else out(resp);
    }

    private void out(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/auth");
    }
}
