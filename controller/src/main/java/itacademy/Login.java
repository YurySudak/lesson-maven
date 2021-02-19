package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/" + RepoConst.LOGIN})
public class Login extends HttpServlet {
    private final static Logger LOG = LoggerFactory.getLogger(Login.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter(RepoConst.LOGIN);
        String pass = req.getParameter(RepoConst.PASS);
        User user = RepositoryService.getUserByLogin(login);
        if (pass.equals(user.getPassword())) {
            String sessionType = "";
            int type = user.getType();
            if (type == 1) sessionType = RepoConst.ADMIN;
            if (type == 2) sessionType = RepoConst.TEACHER;
            if (type == 3) sessionType = RepoConst.STUDENT;
            req.getSession().setAttribute(RepoConst.USER, sessionType);
            req.getSession().setAttribute(RepoConst.LOGIN, login);
            LOG.info(sessionType +" {} logged in", login);
            resp.sendRedirect(sessionType);
            return;
        }
        resp.sendRedirect(ServletPath.AUTH);
    }
}
