package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {"/addteacher"})
public class AddTeacher extends HttpServlet {
    private final static Logger LOG = LoggerFactory.getLogger(AddTeacher.class);
    private final static int AMOUNT_OF_MONTHS = 12;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            LOG.debug("Admin set wrong age: {}", inputAge);
        }
        teacher.setAge(age);
        String groupName = req.getParameter("group_name");
        int groupId =  Db.addGroup(groupName);
        teacher.setGroupId(groupId);
        NamesRepository.setGroup(groupId, groupName);
        List<Double> list = new ArrayList<>();
        for (int i = 1; i <= AMOUNT_OF_MONTHS; i++) {
            double salary = 0;
            String parameter = req.getParameter("z" + i);
            try {
                salary = Double.parseDouble(parameter);
            } catch (NumberFormatException numberFormatException) {
                LOG.debug("Admin added wrong salary: {}", parameter);
            }
            list.add(salary);
        }
        teacher.setSalary(list);
        UsersRepository.addUser(teacher);
        LOG.info("Admin added new teacher = {}", teacher);
        resp.sendRedirect("addteachers");
    }
}
