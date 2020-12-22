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
        writer.write("<p>" + print() + "</p>");
        writer.close();
    }

    private static String print() {
        StringBuilder result = new StringBuilder();
        int[] arr = {1, 5, 6, 8};
        for(int x : arr) {
            result.append(printAll(x)).append("<br>");
        }
        return result.toString();
    }

    private static StringBuilder printAll(int months) {
        StringBuilder result = new StringBuilder("Средние зарплаты за " + months + " месяцев: <br>");
        for (Teacher e : teachers) {
            result.append("ФИО: ").append(e.getName()).append(". Средняя зарплата: ").append(CalcUtil.calcSalary(e, months)).append("<br>");
        }
        return result;
    }
}
