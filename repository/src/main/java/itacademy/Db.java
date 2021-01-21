package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String DRIVER = "org.postgresql.Driver";
    private final static String GET_GROUP_IDS = "select group_id from user_group where user_id = ";

    public static ResultSet getGroups() {
        String sql = "select * from \"group\"";
        return getQuery(sql);
    }

    public static ResultSet getQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "postgres", "admin");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return resultSet;
    }

    public static Integer setGroup(String name) {
        String sql = "insert into \"group\" (group_name) values (?) returning id";
        int id = 0;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "postgres", "admin");
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                id = preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return id;
    }

    public static int addTeacher(Teacher teacher) {
        int id = 0;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "postgres", "admin");
            String sql = "insert into \"user\" (\"type\", fio, age, login, \"password\") " +
                    "values (2, ?, ?, ?, ?) returning id";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, teacher.getFio());
                preparedStatement.setInt(2, teacher.getAge());
                preparedStatement.setString(3, teacher.getLogin());
                preparedStatement.setString(4, teacher.getPassword());
                id = preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return id;
    }

    public static int setSalary(Teacher teacher) {
        List<Double> salary = teacher.getSalary();
        int result = 0;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "postgres", "admin");
            String sql = "insert into salary (teacher_id, month1, month2, month3, month4, month5, month6, month7, month8) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, teacher.getId());
                for (int i = 0; i < 8; i++) {
                    preparedStatement.setDouble(i + 2, salary.get(i));
                }
                result = preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return result;
    }

    public static List<Double> getSalary(int id) {
        List<Double> salary = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "postgres", "admin");
            String sql = "select * from salary where teacher_id = ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql + id);
            if (resultSet.next()) {
                for (int i = 1; i <= 8; i++) {
                    salary.add(resultSet.getDouble("month" + i));
                }
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return salary;
    }

    public static int getGroupId(int id) throws SQLException {
        ResultSet groupSet = getQuery(GET_GROUP_IDS + id);
        int group = 0;
        if (groupSet.next()) {
            group = groupSet.getInt("group_id");
        }
        return group;
    }

    public static List<Integer> getGroupIds(int id) throws SQLException {
        ResultSet groupSet = getQuery(GET_GROUP_IDS + id);
        int group;
        List<Integer> groups = new ArrayList<>();
        while (groupSet.next()) {
            group = groupSet.getInt("group_id");
            groups.add(group);
        }
        return groups;
    }

    public static ResultSet getUsers() {
        String sql = "select * from \"user\"";
        return getQuery(sql);
    }

    public static ResultSet getMarks() {
        String sql = "select * from marks";
        return getQuery(sql);
    }

    public static int updateMarks() {
        List<Marks> marks = MarksRepository.getMarks();
        int result = 0;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, "postgres", "admin");
            String sql = "update marks " +
                    "set mark_of_theme_1 = ?, mark_of_theme_2 = ?, mark_of_theme_3 = ?, mark_of_theme_4 = ?, " +
                    "mark_of_theme_5 = ?, mark_of_theme_6 = ?, mark_of_theme_7 = ?, mark_of_theme_8 = ?, " +
                    "mark_of_theme_9 = ?, mark_of_theme_10 = ?, mark_of_theme_11 = ?, mark_of_theme_12 = ? " +
                    "where id = ?;";
            for (Marks mark : marks) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    List<Integer> marksOfTheme = mark.getMarksOfTheme();
                    for (int i = 0; i < 12; i++) {
                        preparedStatement.setInt(i + 1, marksOfTheme.get(i));
                    }
                    preparedStatement.setInt(13, mark.getId());
                    result += preparedStatement.executeUpdate();
                }
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return result;
    }

    public static void setTeacherGroupId(Teacher teacher) {
        //TODO:

    }
}
