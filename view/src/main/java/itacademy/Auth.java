package itacademy;

import javax.servlet.ServletException;
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
    public void init() throws ServletException {
        super.init();
        RepositoryService.init();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute(Const.USER);
        if (Const.ADMIN.equals(user)) {
            resp.sendRedirect(Const.ADMIN);
            return;
        }
        if (Const.TEACHER.equals(user)) {
            resp.sendRedirect(Const.TEACHER);
            return;
        }
        if (Const.STUDENT.equals(user)) {
            resp.sendRedirect(Const.STUDENT);
            return;
        }

        PrintWriter writer = resp.getWriter();
        String pageName = "Аутентификация";
        writer.write("<head><title>" + pageName + "</title>");
        writer.write(ViewConst.STYLE);
        writer.write("</head><body><h2 align=center>" + pageName + "</h2>");
        writer.write("<form action=\"" + ServletPath.LOGIN + "\" method=\"post\" align=center>");
        writer.write("<p>Логин <input type=\"text\" name=\"" + Const.LOGIN + "\" required></p>");
        writer.write("<p>Пароль <input type=\"password\" name=\"" + Const.PASS + "\" required></p>");
        writer.write("<p><button type=\"submit\">Войти</button></p></form>");

        writer.write("<table align=center><tr><td><b>login</b></td><td><b>password</b></td><td><b>user_type</b></td></tr>\n");
        List<Admin> admins = RepositoryService.getAdmins();
        for (Admin admin : admins) {
            writer.write("<tr><td>" + admin.getLogin() + "</td><td>" + admin.getPassword() + "</td><td>admin<br></td></tr>\n");
        }
        writer.write("<tr></tr>");
        List<Teacher> teachers = RepositoryService.getTeachers();
        for (Teacher teacher : teachers) {
            writer.write("<tr><td>" + teacher.getLogin() + "</td><td>" + teacher.getPassword() + "</td><td>teacher<br></td></tr>\n");
        }
        writer.write("<tr></tr>");
        List<Student> students = RepositoryService.getStudents();
        for (Student student : students) {
            writer.write("<tr><td>" + student.getLogin() + "</td><td>" + student.getPassword() + "</td><td>student<br></td></tr>\n");
        }
        writer.write("</table></body>");
        writer.flush();
        writer.close();
    }
}
