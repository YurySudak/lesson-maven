package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarksRepository {
    private static List<Marks> marks = new ArrayList<>();
    private static boolean isInit = false;
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);
    private static Map<Integer, Marks> marksMap = new HashMap<>();

    public static void init() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Connection connection = DriverManager.getConnection(url, "postgres", "admin");
            String sql = "select * from marks";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static Marks getMarksByStudentAndGroup(Student student, int group) {
        if (!isInit) {
            init();
            isInit = true;
        }
        for (Marks m : marks) {
            if (m.getStudentId() == student.getId() && m.getGroupId() == group)
                return m;
        }
        return null;
    }

    public static List<Marks> getMarks() {
        return new ArrayList<>(marks);
    }

    public static void setMarks(List<Marks> marks) {
        MarksRepository.marks = marks;
    }

    public static void setByThemeAndId(int markOfTheme, int id, int mark) {
        Marks m = marksMap.get(id);
        m.getMarksOfTheme().set(markOfTheme - 1, mark);
    }
}
