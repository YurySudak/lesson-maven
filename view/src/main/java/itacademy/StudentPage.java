package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = {"/student"})
public class StudentPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = (String) req.getSession().getAttribute("user");
        if ("student".equals(user)) {
            String login = (String) req.getSession().getAttribute("login");
            Student student = UsersRepository.getStudentByLogin(login);
            PrintWriter writer = resp.getWriter();
            writer.write("<head><title>Страница студента</title>");
            writer.write("<style>div {margin-left: 20%; }");
            writer.write("table {border-spacing: 20px 10px; }</style></head>");
            writer.write("<body><div><h2>Страница студента</h2>");
            writer.write("<p>ФИО: " + student.fio + "</p>");
            writer.write("<p>Возраст: " + student.age + "</p>");
            writer.write("<table><tr><td><b>Оценки</b></td>");
            for (int i = 1; i <= 12; i++) {
                writer.write("<td>Тема " + i + "</td>");
            }
            writer.write("</tr>");
            for (Integer groupId : student.getGroups()) {
                writer.write("<tr><td>Группа: " + NamesRepository.getGroupNameById(groupId) + "</td>");
                for (int theme = 1; theme <= MarksRepository.amountOfThemes; theme++) {
                    Mark mark = MarksRepository.getMark(student.getId(), groupId, theme);
                    writer.write("<td>" + mark.getValue() + "</td>");
                }
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("<p><a href=\"logout\">Выйти</a></p>");
            writer.write("</div></body>");
            writer.flush();
            writer.close();
        } else resp.sendRedirect("auth");
    }
}
