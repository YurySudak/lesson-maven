package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/" + ServletPath.DELETE_TEACHER})
public class DeleteTeacher extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteTeacher.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("delete");
        LOG.info("Admin deleted teacher = {}", login);
        RepositoryService.deleteUser(login);
        resp.sendRedirect(ServletPath.EDIT_TEACHERS);
    }
}
