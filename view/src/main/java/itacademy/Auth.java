package itacademy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = {"/" + ServletPath.AUTH, "/"})
public class Auth  extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute(RepoConst.USER);
        if (RepoConst.ADMIN.equals(user)) {
            resp.sendRedirect(RepoConst.ADMIN);
            return;
        }
        if (RepoConst.TEACHER.equals(user)) {
            resp.sendRedirect(RepoConst.TEACHER);
            return;
        }
        if (RepoConst.STUDENT.equals(user)) {
            resp.sendRedirect(RepoConst.STUDENT);
            return;
        }

        PrintWriter writer = resp.getWriter();
        String pageName = "Аутентификация";
        writer.write("<head><title>" + pageName + "</title>");
        writer.write(ViewConst.STYLE);
        writer.write("</head><body><h2 align=center>" + pageName + "</h2>");
        writer.write("<form action=\"" + ServletPath.LOGIN + "\" method=\"post\" align=center>");
        writer.write("<p>Логин <input type=\"text\" name=\"" + RepoConst.LOGIN + "\" required></p>");
        writer.write("<p>Пароль <input type=\"password\" name=\"" + RepoConst.PASS + "\" required></p>");
        writer.write("<p><button type=\"submit\">Войти</button></p></form>");

        writer.write("<table align=center><tr><td><b>login</b></td><td><b>password</b></td><td><b>user_type</b></td></tr>\n");
        List<User> admins = RepositoryService.getAdmins();
        for (User admin : admins) {
            writer.write("<tr><td>" + admin.getLogin() + "</td><td>" + admin.getPassword() + "</td><td>admin<br></td></tr>\n");
        }
        writer.write("<tr></tr>");
        List<User> teachers = RepositoryService.getTeachers();
        for (User teacher : teachers) {
            writer.write("<tr><td>" + teacher.getLogin() + "</td><td>" + teacher.getPassword() + "</td><td>teacher<br></td></tr>\n");
        }
        writer.write("<tr></tr>");
        List<User> students = RepositoryService.getStudents();
        for (User student : students) {
            writer.write("<tr><td>" + student.getLogin() + "</td><td>" + student.getPassword() + "</td><td>student<br></td></tr>\n");
        }
        writer.write("</table></body>");
        writer.flush();
        writer.close();
    }
}
