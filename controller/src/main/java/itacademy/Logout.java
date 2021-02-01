package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/" + ServletPath.LOGOUT})
public class Logout extends HttpServlet {
    private final static Logger LOG = LoggerFactory.getLogger(Logout.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute(Const.USER);
        String login = (String) req.getSession().getAttribute(Const.LOGIN);
        LOG.info("{} {} logged out", user, login);
        req.getSession().invalidate();
        resp.sendRedirect(ServletPath.AUTH);
    }
}
