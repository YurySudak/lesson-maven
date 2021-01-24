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
    private final static Logger LOG = LoggerFactory.getLogger(AddTeacher.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        User user = UsersRepository.getUserByLogin(login);
        if (pass.equals(user.getPassword())) {
            String sessionType = "";
            int type = user.getType();
            if (type == 1) sessionType = "admin";
            if (type == 2) sessionType = "teacher";
            if (type == 3) sessionType = "student";
            req.getSession().setAttribute("user", sessionType);
            req.getSession().setAttribute("login", login);
            LOG.info(sessionType +" {} logged in", login);
            resp.sendRedirect(sessionType);
            return;
        }
        resp.sendRedirect("auth");
    }
}
