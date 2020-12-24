package itacademy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Salaries extends HttpServlet {
    private static List<Teacher> teachers = TeachersRepository.getTeachers();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write(printTable());
        writer.close();
    }

    private static String printTable() {
        StringBuilder result = new StringBuilder();
        result.append("<table align=center><tr><td><b>ФИО</b></td>");
        for(int i = 1; i <= 8; i++) {
            String russian = "";
            if (i > 1) russian = "a";
            if (i > 4) russian = "ев";
            result.append("<td>За ").append(i).append(" месяц").append(russian).append("</td>");
        }
        result.append("</tr>");
        for (Teacher teacher : teachers) {
            result.append("<tr>").append(printSalaries(teacher)).append("/tr>");
        }
        result.append("</table>");
        return result.toString();
    }

    private static StringBuilder printSalaries(Teacher teacher) {
        StringBuilder result = new StringBuilder("<td>");
        result.append(teacher.getName()).append("</td>");
        for(int i = 1; i <= 8; i++) {
            result.append("<td>").append(CalcUtil.calcSalary(teacher, i)).append("</td>");
        }
        return result;
    }
}
