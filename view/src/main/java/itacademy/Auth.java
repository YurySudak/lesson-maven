package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = {"/auth", "/"})
public class Auth  extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        UsersRepository.init();
        MarksRepository.init();
        NamesRepository.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = (String) req.getSession().getAttribute("user");
        if ("admin".equals(user)) {
            resp.sendRedirect("admin");
            return;
        }
        if ("teacher".equals(user)) {
            resp.sendRedirect("teacher");
            return;
        }
        if ("student".equals(user)) {
            resp.sendRedirect("student");
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.write("<head><title>Аутентификация</title>");
        writer.write("<style>div {margin-left: 20%; }");
        writer.write("table {border-spacing: 20px 10px; }</style></head>");
        writer.write("<body><h2 align=center>Аутентификация</h2>");
        writer.write("<form action=\"login\" method=\"post\" align=center>");
        writer.write("<p>Логин <input type=\"text\" name=\"login\" required></p>");
        writer.write("<p>Пароль <input type=\"password\" name=\"pass\" required></p>");
        writer.write("<p><button type=\"submit\">Войти</button></p></form>");

        writer.write("<table align=center><tr><td><b>login</b></td><td><b>password</b></td><td><b>user_type</b></td></tr>\n");
        List<Admin> admins = UsersRepository.getAdmins();
        for (Admin admin : admins) {
            writer.write("<tr><td>" + admin.getLogin() + "</td><td>" + admin.getPassword() + "</td><td>admin<br></td></tr>\n");
        }
        List<Teacher> teachers = UsersRepository.getTeachers();
        for (Teacher teacher : teachers) {
            writer.write("<tr><td>" + teacher.getLogin() + "</td><td>" + teacher.getPassword() + "</td><td>teacher<br></td></tr>\n");
        }
        List<Student> students = UsersRepository.getStudents();
        for (Student student : students) {
            writer.write("<tr><td>" + student.getLogin() + "</td><td>" + student.getPassword() + "</td><td>student<br></td></tr>\n");
        }
        writer.write("</table></body>");
        writer.flush();
        writer.close();
    }
}
