package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = {"/" + ServletPath.UPDATE_MARKS})
public class UpdateMarks extends HttpServlet {
    private final static Logger LOG = LoggerFactory.getLogger(UpdateMarks.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Mark> marks = RepositoryService.getMarks();
        for (Mark mark : marks) {
            String rawValue = req.getParameter("mark_id_" + mark.getId());
            if (rawValue != null) {
                int value = 0;
                try {
                    value = Integer.parseInt(rawValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    LOG.debug("Teacher add wrong mark {}", rawValue);
                }
                RepositoryService.setMark(mark.getId(), value);

            }
        }
        int updates = RepositoryService.updateMarks();
        LOG.info("Teacher edited marks, {} marks updated", updates);
        resp.sendRedirect(ServletPath.TEACHER);
    }
}
