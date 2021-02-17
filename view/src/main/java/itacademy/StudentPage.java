package itacademy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = {"/" + ServletPath.STUDENT})
public class StudentPage extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute(RepoConst.USER);
        if (RepoConst.STUDENT.equals(user)) {
            String login = (String) req.getSession().getAttribute(RepoConst.LOGIN);
            User student = RepositoryService.getStudentByLogin(login);
            PrintWriter writer = resp.getWriter();
            String pageName = "Страница студента";
            writer.write("<head><title>" + pageName + "</title>");
            writer.write(ViewConst.STYLE);
            writer.write("</head><body><div><h2>" + pageName + "</h2>");
            writer.write("<p>ФИО: " + student.getFio() + "</p>");
            writer.write("<p>Возраст: " + student.getAge() + "</p>");
            writer.write("<table><tr><td><b>Оценки</b></td>");
            for (int i = 1; i <= 12; i++) {
                writer.write("<td>Тема " + i + "</td>");
            }
            writer.write("</tr>");
            for (Group group : student.getGroups()) {
                writer.write("<tr><td>Группа: " + group.getGroupName() + "</td>");
                for (int theme = 1; theme <= RepositoryService.AMOUNT_OF_THEMES; theme++) {
                    Mark mark = RepositoryService.getMark(student.getId(), group.getId(), theme);
                    writer.write("<td>" + mark.getValue() + "</td>");
                }
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("<p><a href=\"" + ServletPath.LOGOUT + "\">Выйти</a></p>");
            writer.write("</div></body>");
            writer.flush();
            writer.close();
        } else resp.sendRedirect(ServletPath.AUTH);
    }
}
