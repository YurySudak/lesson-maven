package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/logout"})
public class Logout extends HttpServlet {
    private final static Logger LOG = LoggerFactory.getLogger(AddTeacher.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = (String) req.getSession().getAttribute("user");
        String login = (String) req.getSession().getAttribute("login");
        LOG.info("{} {} logged out", user, login);
        req.getSession().invalidate();
        resp.sendRedirect("auth");
    }
}
