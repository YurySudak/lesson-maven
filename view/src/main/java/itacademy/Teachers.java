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
        writer.write("<p>" + print() + "</p>");
        writer.close();
    }

    private static String print() {
        StringBuilder result = new StringBuilder();
        for(Teacher teacher : teachers) {
            result.append(teacher.getName()).append("<br>");
            List<Double> list = teacher.getSalary();
            for (int i = 0; i < list.size(); i++) {
                result.append("Месяц: ").append(i+1);
                result.append(" Зарплата: ").append(list.get(i)).append(" ");
            }
            result.append("<br><br>");
        }
        return result.toString();
    }
}
