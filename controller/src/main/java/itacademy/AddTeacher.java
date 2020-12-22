package itacademy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddTeacher extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        teacher.setName(req.getParameter("fio"));
        List<Double> list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(Double.parseDouble(req.getParameter("z" + i)));
        }
        teacher.setSalary(list);
        List<Teacher> teachers = TeachersRepository.getTeachers();
        teachers.add(teacher);

        resp.sendRedirect("/admin/formteachers");
    }
}
