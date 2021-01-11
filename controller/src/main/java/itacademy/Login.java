package itacademy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if ("admin".equals(UsersRepository.getType(login, pass))) {
            req.getSession().setAttribute("user", "admin");
            req.getSession().setAttribute("login", login);
            resp.sendRedirect("admin");
            return;
        }
        if ("teacher".equals(UsersRepository.getType(login, pass))) {
            req.getSession().setAttribute("user", "teacher");
            req.getSession().setAttribute("login", login);
            resp.sendRedirect("teacher");
            return;
        }
        if ("student".equals(UsersRepository.getType(login, pass))) {
            req.getSession().setAttribute("user", "student");
            req.getSession().setAttribute("login", login);
            resp.sendRedirect("student");
            return;
        }
        resp.sendRedirect("auth");
    }
}
