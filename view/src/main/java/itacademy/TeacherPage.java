package itacademy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = {"/" + ServletPath.TEACHER, "/" + ServletPath.EDIT})
public class TeacherPage extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute(RepoConst.USER);
        if (RepoConst.TEACHER.equals(user)) {
            String login = (String) req.getSession().getAttribute(RepoConst.LOGIN);
            User teacher = RepositoryService.getUserByLogin(login);
            PrintWriter writer = resp.getWriter();
            String pageName = "Страница преподавателя";
            writer.write("<head><title>" + pageName + "</title>");
            writer.write(ViewConst.STYLE);
            writer.write("</head><body><div><h2>" + pageName + "</h2>");
            writer.write("<p>ФИО: " + teacher.getFio()+ "</p>");
            writer.write("<p>Группа: " + RepositoryService.getGroupNameByTeacher(teacher) + "</p>");
            List<User> students = RepositoryService.getStudentsByTeacher(teacher);
            writer.write("<table><tr><td><b>Оценки</b></td>");
            for (int i = 1; i <= 12; i++) {
                writer.write("<td>Тема " + i + "</td>");
            }
            writer.write("</tr>");

            if (("/" + ServletPath.TEACHER).equals(req.getServletPath())) {
                show(teacher, writer, students);
            }

            if (("/" + ServletPath.EDIT).equals(req.getServletPath())) {
                edit(teacher, writer, students);
            }

            writer.write("</div></body>");
            writer.flush();
            writer.close();
        } else resp.sendRedirect(ServletPath.AUTH);
    }

    private void edit(User teacher, PrintWriter writer, List<User> students) {
        for (User student : students) {
            writer.write("<form action=\"" + ServletPath.UPDATE_MARKS + "\" method=\"post\" align=center>");
            writer.write("<tr><td>Студент: " + student.getFio() + "</td>");
            for (int theme = 1; theme <= RepositoryService.AMOUNT_OF_THEMES; theme++) {
                Mark mark = RepositoryService.getMark(student.getId(), teacher.getGroups().iterator().next().getId(), theme);
                String str = "<input type=\"text\" name=\"mark_id_" + mark.getId() + "\" size=\"2\" value=\"" + mark.getValue() + "\">";
                writer.write("<td>" + str + "</td>");
            }
            writer.write("</tr>");
        }
        writer.write("</table><p><button type=\"submit\">Сохранить</button></p></form>");
    }

    private void show(User teacher, PrintWriter writer, List<User> students) {
        if (students.size() == 0) {
            writer.write("<tr><td>У вас пока нет студентов</td></tr></table><p>");
        } else {
            for (User student : students) {
                writer.write("<tr><td>Студент: " + student.getFio() + "</td>");
                for (int theme = 1; theme <= RepositoryService.AMOUNT_OF_THEMES; theme++) {
                    Mark mark = RepositoryService.getMark(student.getId(), teacher.getGroups().iterator().next().getId(), theme);
                    writer.write("<td>" + mark.getValue() + "</td>");
                }
                writer.write("</tr>");
            }
            writer.write("</table><p><a href=\"" + ServletPath.EDIT + "\">Редактировать</a> | ");
        }
        writer.write("<a href=\"" + ServletPath.LOGOUT + "\">Выйти</a></p>");
    }
}
