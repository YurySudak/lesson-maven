package itacademy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Teachers extends HttpServlet {
    private static List<Teacher> teachers = TeachersRepository.getTeachers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write(print());
        writer.close();
    }

    private static String print() {
        StringBuilder result = new StringBuilder();
        result.append("<table border-spacing: 25px 20px; align=center><tr><td><b>Зарплаты</b></td>");
        for(int i = 1; i <= 8; i++) {
            result.append("<td>Месяц: ").append(i).append("</td>");
        }
        result.append("</tr>");
        for(Teacher teacher : teachers) {
            result.append("<tr>");
            result.append("<td>").append(teacher.getName()).append("</td>");
            List<Double> list = teacher.getSalary();
            for (int i = 1; i <= list.size(); i++) {
                result.append("<td>").append(list.get(i - 1)).append("</td>");
            }
            result.append("</tr>");
        }
        result.append("</table>");
        return result.toString();
    }
}
