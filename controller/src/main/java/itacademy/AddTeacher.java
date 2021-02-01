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

@WebServlet(value = {"/" + ServletPath.ADD_TEACHER})
public class AddTeacher extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddTeacher.class);
    private static final int AMOUNT_OF_MONTHS = 12;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Teacher teacher = new Teacher();
        teacher.setLogin(req.getParameter(Const.LOGIN));
        teacher.setPassword(req.getParameter(Const.PASS));
        teacher.setFio(req.getParameter(Const.FIO));
        setAge(req, teacher);
        setGroupId(req, teacher);
        setSalary(req, teacher);
        RepositoryService.addUser(teacher);
        LOG.info("Admin added new teacher = {}", teacher);
        resp.sendRedirect(ServletPath.ADD_TEACHERS);
    }

    private void setSalary(HttpServletRequest req, Teacher teacher) {
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
    }

    private void setGroupId(HttpServletRequest req, Teacher teacher) {
        String groupName = req.getParameter("group_name");
        int groupId =  RepositoryService.addGroup(groupName);
        teacher.setGroupId(groupId);
        RepositoryService.setGroup(groupId, groupName);
    }

    private void setAge(HttpServletRequest req, Teacher teacher) {
        String inputAge = req.getParameter(Const.AGE);
        int age = 0;
        try {
            age = Integer.parseInt(inputAge);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LOG.debug("Admin set wrong age: {}", inputAge);
        }
        teacher.setAge(age);
    }
}
