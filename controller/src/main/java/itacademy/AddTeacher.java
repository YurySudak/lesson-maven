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

@WebServlet(value = {"/" + ServletPath.ADD_TEACHER})
public class AddTeacher extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddTeacher.class);
    private static final int AMOUNT_OF_MONTHS = RepositoryService.AMOUNT_OF_THEMES;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User teacher = new User();
        teacher.setLogin(req.getParameter(RepoConst.LOGIN));
        teacher.setPassword(req.getParameter(RepoConst.PASS));
        teacher.setFio(req.getParameter(RepoConst.FIO));
        setAge(req, teacher);
        setSalary(req, teacher);
        try {
            RepositoryService.addUser(teacher);
            LOG.info("Admin added new teacher = {}", teacher);
            setGroupId(req);
        } catch (ExistException e) {
            req.setAttribute("exception", e);
            LOG.info("Admin tried to add teacher with existing login = {}", teacher);
        }
        resp.sendRedirect(ServletPath.ADD_TEACHERS);
    }

    private void setSalary(HttpServletRequest req, User teacher) {
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
        RepositoryService.setSalary(teacher, list);
    }

    private void setGroupId(HttpServletRequest req) {
        String groupName = req.getParameter("group_name");
        try {
            RepositoryService.addGroup(groupName);
        } catch (ExistException e) {
            req.setAttribute("exception", e);
            LOG.info("Admin tried to add teacher with existing group = {}", groupName);
        }
    }

    private void setAge(HttpServletRequest req, User teacher) {
        String inputAge = req.getParameter(RepoConst.AGE);
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
