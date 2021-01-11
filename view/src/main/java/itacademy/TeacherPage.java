package itacademy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = {"/teacher"})
public class TeacherPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute("user");
        if ("teacher".equals(user)) {
            String login = (String) req.getSession().getAttribute("login");
            Teacher teacher = UsersRepository.getTeacherByLogin(login);
            PrintWriter writer = resp.getWriter();
            writer.write("<head><title>Кабинет тренера</title>");
            writer.write("<style>div {margin-left: 30%; }");
            writer.write("table {border-spacing: 20px 10px; }</style></head>");
            writer.write("<body><div><h2>Кабинет тренера</h2>");
            writer.write("<p>ФИО: " + teacher.fio + "</p>");
            writer.write("<p>Возраст: " + teacher.age);
            List<Student> students = UsersRepository.getStudentsByTeacher(teacher);
            writer.write("<table><tr><td><b>Оценки</b></td>");
            for (int i = 1; i <= 12; i++) {
                writer.write("<td>Тема " + i + "</td>");
            }
            writer.write("</tr>");
            for (Student student : students) {
                Marks marks = MarksRepository.getMarksByStudent(student);
                writer.write("<tr><td>Студент: " + student.getFio() + "</td>");
                for (int i = 1; i <= 12; i++) {
                    String mark = "-";
                    if (marks.getMarksOfTheme() != null) {
                        if (marks.getMarksOfTheme().get(i) != null) {
                            mark = marks.getMarksOfTheme().get(i).toString();
                        }
                    }
                    writer.write("<td>" + mark + "</td>");
                }
                writer.write("</tr>");
            }
            writer.write("</table></div></body>");
            writer.flush();
            writer.close();
        } else resp.sendRedirect("auth");

    }
}
