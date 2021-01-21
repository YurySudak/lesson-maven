package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarksRepository {
    private static final List<Marks> marks = new ArrayList<>();
    private static final Map<Integer, Marks> marksMap = new HashMap<>();
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);

    public static void init() {
        try {
            ResultSet resultSet = Db.getMarks();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int groupId = resultSet.getInt("group_id");
                int studentId = resultSet.getInt("student_id");
                List<Integer> list = new ArrayList<>();
                for (int i = 1; i <= 12; i++) {
                    int mark = resultSet.getInt("mark_of_theme_" + i);
                    list.add(mark);
                }
                Marks mark = new Marks(id, groupId, studentId, list);
                marks.add(mark);
                marksMap.put(id, mark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static Marks getMarksByStudentAndGroup(Student student, int group) {
        for (Marks m : marks) {
            if (m.getStudentId() == student.getId() && m.getGroupId() == group)
                return m;
        }
        return null;
    }

    public static List<Marks> getMarks() {
        return new ArrayList<>(marks);
    }

    public static void setByThemeAndId(int markOfTheme, int id, int mark) {
        Marks m = marksMap.get(id);
        m.getMarksOfTheme().set(markOfTheme - 1, mark);
    }
}
