package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarksRepository {
    private static List<Marks> marks = new ArrayList<>();
    private static boolean isInit = false;
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);

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
                    String column = "mark_of_theme_";
                    if (i < 10) {
                        column += "0" + i;
                    } else {
                        column += i;
                    }
                    int mark = resultSet.getInt(column);
                    if (!resultSet.wasNull()) {
                        list.set(i, mark);
                    }
                }
                marks.add(new Marks(id, groupId, studentId, list));
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static Marks getMarksByStudent(Student student) {
        for (Marks m : marks) {
            if (m.getStudentId() == student.getId())
                return m;
        }
        return null;
    }
}
