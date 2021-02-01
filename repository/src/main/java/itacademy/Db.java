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
    private final static Logger LOG = LoggerFactory.getLogger(Db.class);
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String DRIVER = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    private final static String GET_GROUP_IDS = "select group_id from user_group where user_id = ";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
    }

    public static ResultSet getGroups() {
        String sql = "select * from \"group\"";
        return getQuery(sql);
    }

    public static ResultSet getUsers() {
        String sql = "select * from \"user\"";
        return getQuery(sql);
    }

    public static ResultSet getMarks() {
        String sql = "select * from mark";
        return getQuery(sql);
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

    private static ResultSet getQuery(String sql) {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
        return resultSet;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static int addUser(User user) {
        int id = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into \"user\" (\"type\", fio, age, login, \"password\") " +
                    "values (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, user.getType());
                preparedStatement.setString(2, user.getFio());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.executeUpdate();
                connection.commit();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
                resultSet.close();
            }
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
        return id;
    }

    public static int setSalary(Teacher teacher) {
        List<Double> salary = teacher.getSalary();
        int result = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            int i = 1;
            String sql = "insert into salary (teacher_id, month, value) values (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (double value : salary) {
                    preparedStatement.setInt(1, teacher.getId());
                    preparedStatement.setInt(2, i++);
                    preparedStatement.setDouble(3, value);
                    result = preparedStatement.executeUpdate();
                    connection.commit();
                }
            }
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
        return result;
    }

    public static List<Double> getSalary(int teacherId) {
        List<Double> salary = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "select * from salary where teacher_id = ? and month = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (int i = 1; i <= 12; i++) {
                    preparedStatement.setInt(1, teacherId);
                    preparedStatement.setInt(2, i);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    connection.commit();
                    double value = 0;
                    if (resultSet.next()) {
                        value = resultSet.getDouble("value");
                    }
                    salary.add(value);
                }
            }
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
        return salary;
    }

    public static int updateMarks() {
        List<Mark> marks = MarksRepository.getMarks();
        int result = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "update mark set value = ?, group_id = ?, student_id = ?, theme = ? where id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Mark mark : marks) {
                    preparedStatement.setInt(1, mark.getValue());
                    preparedStatement.setInt(2, mark.getGroupId());
                    preparedStatement.setInt(3, mark.getStudentId());
                    preparedStatement.setInt(4, mark.getTheme());
                    preparedStatement.setInt(5, mark.getId());
                    result = preparedStatement.executeUpdate();
                    connection.commit();
                }
            }
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
        return result;
    }

    public static int addGroup(String groupName) {
        int id = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into \"group\" (group_name) values (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, groupName);
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                connection.commit();
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
                resultSet.close();
            }
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
        return id;
    }

    public static Mark addMark(int studentId, int groupId, int theme) {
        int id = 0;
        int defaultValue = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into \"mark\" (student_id, group_id, theme, value) values (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, groupId);
                preparedStatement.setInt(3, theme);
                preparedStatement.setInt(4, defaultValue);
                preparedStatement.executeUpdate();
                connection.commit();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
                resultSet.close();
            }
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
        return new Mark(id, groupId,studentId, theme, defaultValue);
    }

    public static void linkUserGroup(int userId, int groupId) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into user_group (user_id, group_id) values (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, groupId);
                preparedStatement.executeUpdate();
                connection.commit();
            }
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage());
            }
            LOG.error(e.getMessage());
        }
    }
}
