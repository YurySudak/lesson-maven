package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MarksRepository {
    private static final List<Mark> marks = Collections.synchronizedList(new ArrayList<>());
    private static final Map<Integer, Mark> marksMap = new ConcurrentHashMap<>();
    private final static Logger LOG = LoggerFactory.getLogger(MarksRepository.class);
    public final static int amountOfThemes = 12;

    public static void init() {
        try {
            ResultSet resultSet = Db.getMarks();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int groupId = resultSet.getInt("group_id");
                int studentId = resultSet.getInt("student_id");
                int theme = resultSet.getInt("theme");
                int value = resultSet.getInt("value");
                Mark mark = new Mark(id, groupId, studentId, theme, value);
                marks.add(mark);
                marksMap.put(id, mark);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    public static Mark getMark(int studentId, int groupId, int theme) {
        for (Mark mark : marks) {
            if (mark.getStudentId() == studentId && mark.getGroupId() == groupId && mark.getTheme() == theme)
                return mark;
        }
        Mark mark = Db.addMark(studentId, groupId, theme);
        marks.add(mark);
        marksMap.put(mark.getId(), mark);
        return mark;
    }

    public static List<Mark> getMarks() {
        return new ArrayList<>(marks);
    }

    public static void setMark(int id, int value) {
        Mark mark = marksMap.get(id);
        mark.setValue(value);
    }
}
