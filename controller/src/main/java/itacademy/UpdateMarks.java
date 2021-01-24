package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = {"/updateMarks"})
public class UpdateMarks extends HttpServlet {
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Mark> marks = MarksRepository.getMarks();
        for (Mark mark : marks) {
            String rawValue = req.getParameter("mark_id_" + mark.getId());
            if (rawValue != null) {
                int value = 0;
                try {
                    value = Integer.parseInt(rawValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    log.debug("Teacher add wrong mark {}", rawValue);
                }
                MarksRepository.setMark(mark.getId(), value);
            }
        }
        int updates = Db.updateMarks();
        log.info("Teacher edited marks, {} marks updated", updates);
        resp.sendRedirect("teacher");
    }
}
