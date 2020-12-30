package itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = {"/teacher/*"})
public class TeacherPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Teacher trainer = null;
        for (Teacher teacher : TeachersRepository.getTeachers()) {
            if (req.getRequestURI().contains(teacher.getLogin())) {
                trainer = teacher;
                break;
            }
        }
        writer.write("<div align=center><h2>Кабинет тренера</h2>");
        writer.write("<p>ФИО: " + trainer.fio + "</p>");
        writer.write("<p>Возраст: " + trainer.age + "</p></div>");
    }
}
