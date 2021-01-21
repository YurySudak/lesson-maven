package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = {"/admin", "/addteachers"})
public class AdminPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = (String) req.getSession().getAttribute("user");
        if ("admin".equals(user)) {
            PrintWriter writer = resp.getWriter();
            writer.write("<head><title>Страница администратора</title>");
            writer.write("<style>div {margin-left: 20%; }");
            writer.write("table {border-spacing: 20px 10px; }</style></head>");
            writer.write("<body><div>");

            if ("/admin".equals(req.getServletPath())) {
                menu(writer, true);
                salaries(writer);
            }
            if ("/addteachers".equals(req.getServletPath())) {
                menu(writer, false);
                addteachers(writer);
            }
            writer.write("</div></body>");
            writer.flush();
            writer.close();
        } else resp.sendRedirect("auth");
    }

    private void addteachers(PrintWriter writer) {
        writer.write("<h2 align=center>Ввести новых преподавателей</h2>\n");
        writer.write("<form action=\"addteacher\" method=\"post\">\n");
        writer.write("<p>Логин <input type=\"text\" name=\"login\" size=\"10\" required value=\"lok\"></p>\n");
        writer.write("<p>Пароль <input type=\"text\" name=\"pass\" size=\"10\" required value=\"lok\"></p>\n");
        writer.write("<p>ФИО <input type=\"text\" name=\"fio\" size=\"40\" required value=\"Локтев Алексей Алексеевич\"></p>\n");
        writer.write("<p>Возраст <input type=\"text\" name=\"age\" size=\"5\" required value=\"25\"></p>\n");
        writer.write("<p>Группа <input type=\"text\" name=\"group\" size=\"40\" required value=\"React and Angular\"></p>\n");
        for (int i = 1; i <=8; i++) {
            writer.write("<p>Зарплата за " + i + " месяц <input type=\"text\" name=\"z");
            writer.write(i +"\" size=\"5\" required value=\"1500\"></p>\n");
        }
        writer.write("<p><button type=\"submit\">Ввести</button></p></form>\n");
        teachers(writer);
    }

    private void teachers(PrintWriter writer) {
        StringBuilder result = new StringBuilder();
        result.append("<table border-spacing: 25px 20px; align=center><tr><td><b>Зарплаты</b></td>");
        for(int i = 1; i <= 8; i++) {
            result.append("<td>Месяц: ").append(i).append("</td>");
        }
        result.append("</tr>");
        List<Teacher> teachers = UsersRepository.getTeachers();
        for(Teacher teacher : teachers) {
            result.append("<tr>");
            result.append("<td>").append(teacher.getFio()).append("</td>");
            List<Double> list = teacher.getSalary();
            for (int i = 1; i <= list.size(); i++) {
                result.append("<td>").append(list.get(i - 1)).append("</td>");
            }
            result.append("</tr>");
        }
        result.append("</table>");
        writer.write(result.toString());
    }

    private void menu(PrintWriter writer, boolean isSalaries) {
        writer.write("<nav align=center>");
        if (isSalaries) {
            writer.write("Средние зарплаты преподавателей | <a href=\"addteachers\">Ввести новых преподавателей</a>");
        } else {
            writer.write("<a href=\"admin\">Средние зарплаты преподавателей</a> | Ввести новых преподавателей");
        }
        writer.write(" | <a href=\"logout\">Выйти</a></nav>");
    }

    private void salaries(PrintWriter writer) {
        writer.write("<h2 align=center>Средние зарплаты преподавателей</h2>");
        StringBuilder result = new StringBuilder();
        result.append("<table align=center><tr><td><b>ФИО</b></td>");
        for(int i = 1; i <= 8; i++) {
            String russian = "";
            if (i > 1) russian = "a";
            if (i > 4) russian = "ев";
            result.append("<td>За ").append(i).append(" месяц").append(russian).append("</td>");
        }
        result.append("</tr>");
        List<Teacher> teachers = UsersRepository.getTeachers();
        for (Teacher teacher : teachers) {
            result.append("<tr>").append(printSalaries(teacher)).append("</tr>");
        }
        result.append("</table>");
        writer.write(result.toString());

    }

    private static StringBuilder printSalaries(Teacher teacher) {
        StringBuilder result = new StringBuilder();
        result.append("<td>").append(teacher.getFio()).append("</td>");
        for(int i = 1; i <= 8; i++) {
            result.append("<td>").append(CalcUtil.calcSalary(teacher, i)).append("</td>");
        }
        return result;
    }
}
