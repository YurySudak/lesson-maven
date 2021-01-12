package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/login"})
public class Login extends HttpServlet {
    private final static Logger log = LoggerFactory.getLogger(AddTeacher.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if ("admin".equals(UsersRepository.getType(login, pass))) {
            req.getSession().setAttribute("user", "admin");
            req.getSession().setAttribute("login", login);
            log.info("Admin {} logged in", login);
            resp.sendRedirect("admin");
            return;
        }
        if ("teacher".equals(UsersRepository.getType(login, pass))) {
            req.getSession().setAttribute("user", "teacher");
            req.getSession().setAttribute("login", login);
            log.info("Teacher {} logged in", login);
            resp.sendRedirect("teacher");
            return;
        }
        if ("student".equals(UsersRepository.getType(login, pass))) {
            req.getSession().setAttribute("user", "student");
            req.getSession().setAttribute("login", login);
            log.info("Student {} logged in", login);
            resp.sendRedirect("student");
            return;
        }
        resp.sendRedirect("auth");
    }
}
