package itacademy;

import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = {"/" + ServletPath.ADMIN, "/" + ServletPath.EDIT_TEACHERS, "/" + ServletPath.AVERAGE_SALARIES})
public class AdminPage extends HttpServlet {
    private final static int AMOUNT_OF_MONTHS = 12;
    private static final String ADMIN_PAGE = "Страница администратора";
    private static final String EDIT_TEACHERS = "Редактировать преподавателей";
    private static final String AVERAGE_SALARIES = "Средние зарплаты преподавателей";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String) req.getSession().getAttribute(RepoConst.USER);
        if (RepoConst.ADMIN.equals(user)) {
            PrintWriter writer = resp.getWriter();
            writer.write("<head><title>" + ADMIN_PAGE + "</title>");
            writer.write(ViewConst.STYLE);
            writer.write("</head><body><div>");

            if (("/" + ServletPath.ADMIN).equals(req.getServletPath())) {
                menu(writer, true);
                printTeachersAndAverageSalaries(writer);
            }
            if (("/" + ServletPath.EDIT_TEACHERS).equals(req.getServletPath())) {
                menu(writer, false);
                editTeachers(writer, req);
                printTeachersAndSalaries(writer);
            }
            writer.write("</div></body>");
            writer.flush();
            writer.close();
        } else resp.sendRedirect(ServletPath.AUTH);
    }

    private void editTeachers(PrintWriter writer, HttpServletRequest req) {
        writer.write("<h2 align=center>" + EDIT_TEACHERS + "</h2>\n");
        writer.write("<form action=\"" + ServletPath.ADD_TEACHER  + "\" method=\"post\">\n");
        writer.write("<p>Логин <input type=\"text\" name=\"login\" size=\"10\" required value=\"lok\"></p>\n");
        writer.write("<p>Пароль <input type=\"text\" name=\"pass\" size=\"10\" required value=\"lok\"></p>\n");
        writer.write("<p>ФИО <input type=\"text\" name=\"fio\" size=\"40\" required value=\"Локтев Алексей Алексеевич\"></p>\n");
        writer.write("<p>Возраст <input type=\"text\" name=\"age\" size=\"5\" required value=\"25\"></p>\n");
        writer.write("<p>Группа <input type=\"text\" name=\"group_name\" size=\"40\" required value=\"React and Angular\"></p>\n");
        writer.write("<table><tr><td>Месяц</td>");
        for (int i = 1; i <= AMOUNT_OF_MONTHS; i++) {
            writer.write("<td>" + i + "</td>");
        }
        writer.write("</tr><tr><td>Зарплата</td>");
        for (int i = 1; i <= AMOUNT_OF_MONTHS; i++) {
            writer.write("<td><input type=\"text\" name=\"z");
            writer.write(i +"\" size=\"5\" required value=\"1500\"></td>\n");
        }
        writer.write("</tr></table>");
        writer.write("<p><button type=\"submit\">Ввести нового преподавателя</button></p></form>\n");
        String attr = (String) req.getSession().getAttribute("exception");
        if (attr != null) {
            writer.write("<p>" + attr + "</p>");
            req.getSession().removeAttribute("exception");
        }
    }

    private void printTeachersAndSalaries(PrintWriter writer) {
        StringBuilder result = new StringBuilder();
        result.append("<table><tr><td><b>Зарплаты</b></td>");
        for(int i = 1; i <= AMOUNT_OF_MONTHS; i++) {
            result.append("<td>Месяц: ").append(i).append("</td>");
        }
        result.append("</tr>\n");
        List<User> teachers = RepositoryService.getTeachers();
        for(User teacher : teachers) {
            result.append("<tr>");
            result.append("<td>").append(teacher.getFio()).append("</td>");
            for (int i = 1; i <= RepositoryService.AMOUNT_OF_MONTHS; i++) {
                result.append("<td>").append(RepositoryService.getSalary(teacher, i).getValue()).append("</td>");
            }
            result.append("<td><form action=\"")
                    .append(ServletPath.DELETE_TEACHER)
                    .append("\" method=\"post\">")
                    .append("<button type=\"submit\" name=\"delete\" value=\"")
                    .append(teacher.getLogin())
                    .append("\">Удалить преподавателя</button></form>")
                    .append("</td>");
            result.append("</tr>\n");
        }
        result.append("</table>");
        writer.write(result.toString());
    }

    private void menu(PrintWriter writer, boolean isSalaries) {
        writer.write("<nav align=center>");
        if (isSalaries) {
            writer.write(AVERAGE_SALARIES + " | <a href=\"" + ServletPath.EDIT_TEACHERS + "\">" + EDIT_TEACHERS + "</a>");
        } else {
            writer.write("<a href=\"" + ServletPath.ADMIN + "\">" + AVERAGE_SALARIES + "</a> | " + EDIT_TEACHERS + "");
        }
        writer.write(" | <a href=\"" + ServletPath.LOGOUT + "\">Выйти</a></nav>");
    }

    private void printTeachersAndAverageSalaries(PrintWriter writer) {
        writer.write("<h2 align=center>" + AVERAGE_SALARIES + "</h2>");
        StringBuilder result = new StringBuilder();
        result.append("<table><tr><td><b>ФИО</b></td>");
        for(int i = 1; i <= AMOUNT_OF_MONTHS; i++) {
            String russian = "";
            if (i > 1) russian = "a";
            if (i > 4) russian = "ев";
            result.append("<td>За ").append(i).append(" месяц").append(russian).append("</td>");
        }
        result.append("</tr>");
        List<User> teachers = RepositoryService.getTeachers();
        for (User teacher : teachers) {
            result.append("<tr>").append(printAverageSalaries(teacher)).append("</tr>");
        }
        result.append("</table>");
        writer.write(result.toString());

    }

    private static StringBuilder printAverageSalaries(User teacher) {
        StringBuilder result = new StringBuilder();
        result.append("<td>").append(teacher.getFio()).append("</td>");
        for(int i = 1; i <= AMOUNT_OF_MONTHS; i++) {
            result.append("<td>").append(CalcUtil.calcSalary(teacher, i)).append("</td>");
        }
        return result;
    }
}
