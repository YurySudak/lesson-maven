package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/" + ServletPath.DELETE_TEACHER})
public class DeleteTeacher extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("delete");
        RepositoryService.deleteTeacher(login);
        resp.sendRedirect(ServletPath.ADD_TEACHERS);
    }
}
