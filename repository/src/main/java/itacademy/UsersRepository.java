package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersRepository {
    private static Map<Integer, String> groups = new HashMap<>();
    private static List<Admin> admins = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Map<String, Admin> adminsMap = new HashMap();
    private static Map<String, Teacher> teachersMap = new HashMap();
    private static Map<Integer, Student> studentsMap = new HashMap();
    private static boolean isInit = false;
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);

    public static void init() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            Connection connection = DriverManager.getConnection(url, "postgres", "admin");
            String sql = "select * from \"user\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fio = resultSet.getString("fio");
                int age = resultSet.getInt("age");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("password");
                int type = resultSet.getInt("type");

                if (type == 1) {
                    admins.add(new Admin(id, fio, age, login, pass));
                }
                if (type == 2) {
                    int group = resultSet.getInt("group1_id");
                    teachers.add(new Teacher(id, fio, age, login, pass, group));
                }
                if (type == 3) {
                    int group1 = resultSet.getInt("group1_id");
                    int group2 = resultSet.getInt("group2_id");
                    int group3 = resultSet.getInt("group3_id");
                    List<Integer> groups = List.of(group1, group2, group3);
                    students.add(new Student(id, fio, age, login, pass, groups));
                }
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        teachers.get(0).setSalary(List.of(1400.0, 1400.0, 1400.0, 1400.0, 1600.0, 1600.0, 1600.0, 1600.0));
        teachers.get(1).setSalary(List.of(1500.0, 1500.0, 1500.0, 1500.0, 1600.0, 1600.0, 1600.0, 1600.0));
        teachers.get(2).setSalary(List.of(1600.0, 1600.0, 1600.0, 1600.0, 1800.0, 1800.0, 1800.0, 1800.0));

        for (Admin admin : admins) {
            adminsMap.put(admin.getLogin(), admin);
        }
        for (Teacher teacher : teachers) {
            teachersMap.put(teacher.getLogin(), teacher);
        }
        for (Student student : students) {
            studentsMap.put(student.getId(), student);
        }
    }

    public static void add(User user) {
        checkInit();
        if (user instanceof Admin) admins.add((Admin) user);
        if (user instanceof Teacher) teachers.add((Teacher) user);
        if (user instanceof Student) students.add((Student) user);
    }

    public static String getType(String login, String pass) {
        checkInit();
        for (User user : admins) {
            if (login.equals(user.getLogin()) && pass.equals(user.getPassword())) {
                return "admin";
            }
        }
        for (User user : teachers) {
            if (login.equals(user.getLogin()) && pass.equals(user.getPassword())) {
                return "teacher";
            }
        }
        for (User user : students) {
            if (login.equals(user.getLogin()) && pass.equals(user.getPassword())) {
                return "student";
            }
        }
        return null;
    }

    private static void checkInit() {
        if (!isInit) {
            init();
            isInit = true;
        }
    }

    public static Teacher getTeacherByLogin(String login) {
        return teachersMap.get(login);
    }

    public static Student getStudentById(int studentId) {
        return studentsMap.get(studentId);
    }

    public static List<Student> getStudentsByTeacher(Teacher teacher) {
        List<Student> list = new ArrayList<>();
        for (Student student : students) {
            for (int group : student.getGroups()) {
                if (teacher.getGroup() == group) {
                    list.add(student);
                }
            }
        }
        return list;
    }
}
