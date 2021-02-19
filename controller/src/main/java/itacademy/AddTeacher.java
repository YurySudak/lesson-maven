package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/" + ServletPath.ADD_TEACHER})
public class AddTeacher extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddTeacher.class);
    private static final int AMOUNT_OF_MONTHS = RepositoryService.AMOUNT_OF_MONTHS;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter(RepoConst.LOGIN);
        if (!RepositoryService.checkLogin(login)) {
            User teacher = new User();
            teacher.setLogin(login);
            teacher.setPassword(req.getParameter(RepoConst.PASS));
            teacher.setFio(req.getParameter(RepoConst.FIO));
            setAge(req, teacher);
            teacher.setType(2);
            Group group = setGroup(req);
            teacher.addGroup(group);
            RepositoryService.addUser(teacher);
            LOG.info("Admin added new teacher = {}", teacher);
            setSalary(req, teacher);
        } else {
            req.getSession().setAttribute("exception", "Такой логин уже существует: " + login);
            LOG.error("Admin tried to add teacher with existing login = {}", login);
        }
        resp.sendRedirect(ServletPath.EDIT_TEACHERS);
    }

    private void setSalary(HttpServletRequest req, User teacher) {
        for (int month = 1; month <= AMOUNT_OF_MONTHS; month++) {
            double salary = 0;
            String parameter = req.getParameter("z" + month);
            try {
                salary = Double.parseDouble(parameter);
            } catch (NumberFormatException numberFormatException) {
                LOG.debug("Admin added wrong salary: {}", parameter);
            }
            RepositoryService.setSalary(teacher, month, salary);
        }
    }

    private Group setGroup(HttpServletRequest req) {
        String groupName = req.getParameter("group_name");
        return RepositoryService.addGroup(groupName);
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