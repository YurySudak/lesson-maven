package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {"/addteacher"})
public class AddTeacher extends HttpServlet {
    private final static Logger log = LoggerFactory.getLogger(AddTeacher.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = new Teacher();
        teacher.setLogin(req.getParameter("login"));
        teacher.setPassword(req.getParameter("pass"));
        teacher.setFio(req.getParameter("fio"));
        String inputAge = req.getParameter("age");
        int age = 0;
        try {
            age = Integer.parseInt(inputAge);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.debug("Admin set wrong age: {}", inputAge);
        }
        teacher.setAge(age);
        String group = req.getParameter("group");
        teacher.setGroupId(NamesRepository.getGroupIdByName(group));
        List<Double> list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            double salary = 0;
            String parameter = req.getParameter("z" + i);
            try {
                salary = Double.parseDouble(parameter);
            } catch (NumberFormatException numberFormatException) {
                log.debug("Admin added wrong salary: {}", parameter);
            }
            list.add(salary);
        }
        teacher.setSalary(list);
        UsersRepository.add(teacher);
        log.info("Admin added new teacher = {}", teacher);
        resp.sendRedirect("addteachers");
    }
}
