package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Marks> marks = MarksRepository.getMarks();
        for (int markOfTheme = 1; markOfTheme <= 12; markOfTheme++) {
            for (Marks m : marks) {
                String mark = req.getParameter("mark_of_theme_" + markOfTheme + "_of_mark_id_" + m.getId());
                if (mark != null) {
                    int value = 0;
                    try {
                        value = Integer.parseInt(mark);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        log.debug("Teacher add wrong mark {}", mark);
                    }
                    MarksRepository.setByThemeAndId(markOfTheme, m.getId(), value);
                }
            }
        }
        int updates = Db.updateMarks();
        log.info("Teacher edited marks, {} marks updated", updates);
        resp.sendRedirect("teacher");
    }
}
