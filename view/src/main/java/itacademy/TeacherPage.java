package itacademy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = {"/teacher", "/edit"})
public class TeacherPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute("user");
        if ("teacher".equals(user)) {
            String login = (String) req.getSession().getAttribute("login");
            Teacher teacher = UsersRepository.getTeacherByLogin(login);
            PrintWriter writer = resp.getWriter();
            writer.write("<head><title>Страница преподавателя</title>");
            writer.write("<style>div {margin-left: 20%; }");
            writer.write("table {border-spacing: 20px 10px; }</style></head>");
            writer.write("<body><div><h2>Страница преподавателя</h2>");
            writer.write("<p>ФИО: " + teacher.fio + "</p>");
            writer.write("<p>Группа: " + NamesRepository.getGroupNameById(teacher.getGroupId()) + "</p>");
            List<Student> students = UsersRepository.getStudentsByTeacher(teacher);
            writer.write("<table><tr><td><b>Оценки</b></td>");
            for (int i = 1; i <= 12; i++) {
                writer.write("<td>Тема " + i + "</td>");
            }
            writer.write("</tr>");


            if ("/teacher".equals(req.getServletPath())) {
                show(teacher, writer, students);
            }

            if ("/edit".equals(req.getServletPath())) {
                edit(teacher, writer, students);
            }



            writer.write("</div></body>");
            writer.flush();
            writer.close();
        } else resp.sendRedirect("auth");
    }

    private void edit(Teacher teacher, PrintWriter writer, List<Student> students) {
        for (Student student : students) {
            Marks marks = MarksRepository.getMarksByStudentAndGroup(student, teacher.getGroupId());
            writer.write("<form action=\"updateMarks\" method=\"post\" align=center>");
            writer.write("<tr><td>Студент: " + student.getFio() + "</td>");
            for (int i = 0; i < 12; i++) {
                int mark = -1;
                if (marks.getMarksOfTheme() != null) {
                    mark = marks.getMarksOfTheme().get(i);
                }
                int num = i + 1;
                String str = "<input type=\"text\" name=\"mark_of_theme_" + num + "_of_mark_id_" + marks.getId() + "\" size=\"2\" value=\"" + mark + "\">";
                writer.write("<td>" + str + "</td>");
            }
            writer.write("</tr>");
        }
        writer.write("</table><p><button type=\"submit\">Сохранить</button></p></form>");
    }

    private void show(Teacher teacher, PrintWriter writer, List<Student> students) {
        for (Student student : students) {
            Marks marks = MarksRepository.getMarksByStudentAndGroup(student, teacher.getGroupId());
            writer.write("<tr><td>Студент: " + student.getFio() + "</td>");
            for (int i = 0; i < 12; i++) {
                int mark = -1;
                if (marks.getMarksOfTheme() != null) {
                    mark = marks.getMarksOfTheme().get(i);
                }
                writer.write("<td>" + mark + "</td>");
            }
            writer.write("</tr>");
        }
        writer.write("</table><p><a href=\"edit\">Редактировать</a> | ");
        writer.write("<a href=\"logout\">Выйти</a></p>");
    }
}
