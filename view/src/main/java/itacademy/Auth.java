package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = {"/auth", "/"})
public class Auth  extends HttpServlet {

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
        writer.write("<p>Логин <input type=\"text\" placeholder=\"Введите логин\" name=\"login\" required></p>");
        writer.write("<p>Пароль <input type=\"password\" placeholder=\"Введите пароль\" name=\"pass\" required></p>");
        writer.write("<p><button type=\"submit\">Войти</button></p></form>");

        writer.write("<table align=center><tr><td>login</td><td>password</td><td>user_type</td></tr>\n" +
                "<tr><td>sud</td><td>sud</td><td>admin<br></td></tr>\n" +
                "<tr><td>vlas</td><td>vlas</td><td>teacher<br></td></tr>\n" +
                "<tr><td>kal</td><td>kal</td><td>teacher<br></td></tr>\n" +
                "<tr><td>prud</td><td>prud</td><td>teacher</td></tr></table>");
        writer.write("</body>");
        writer.flush();
        writer.close();
    }
}
