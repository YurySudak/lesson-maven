package itacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersRepository {
    private static final List<Admin> admins = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final Map<String, Admin> adminsMap = new HashMap<>();
    private static final Map<String, Teacher> teachersMap = new HashMap<>();
    private static final Map<String, Student> studentsMap = new HashMap<>();
    private static final Map<String, User> usersMap = new HashMap<>();
    private final static Logger log = LoggerFactory.getLogger(UsersRepository.class);

    public static void init() {
        ResultSet resultSet = Db.getUsers();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fio = resultSet.getString("fio");
                int age = resultSet.getInt("age");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("password");
                int type = resultSet.getInt("type");
                User user = new User(id, fio, age, login, pass);
                usersMap.put(login, user);

                if (type == 1) {
                    Admin admin = new Admin(id, fio, age, login, pass);
                    admins.add(admin);
                    adminsMap.put(login, admin);
                }
                if (type == 2) {
                    int group = Db.getGroupId(id);
                    List<Double> salary = Db.getSalary(id);
                    Teacher teacher = new Teacher(id, fio, age, login, pass, group, salary);
                    teachers.add(teacher);
                    teachersMap.put(login, teacher);
                }
                if (type == 3) {
                    List<Integer> groups = Db.getGroupIds(id);
                    Student student = new Student(id, fio, age, login, pass, groups);
                    students.add(student);
                    studentsMap.put(login, student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static void add(User user) {
        if (user instanceof Admin) {
            admins.add((Admin) user);
        }
        if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            int id = Db.addTeacher(teacher);
            teacher.setId(id);
            Db.setSalary(teacher);
            Db.setTeacherGroupId(teacher);
            teachers.add(teacher);
            String login = teacher.getLogin();
            teachersMap.put(login, teacher);
            usersMap.put(login, teacher);
        }
        if (user instanceof Student) {
            students.add((Student) user);
        }
    }

    public static String getType(String login, String pass) {
        User user = usersMap.get(login);
        String password = user.getPassword();
        if (pass.equals(password)) {
            if (adminsMap.containsKey(login)) return "admin";
            if (teachersMap.containsKey(login)) return "teacher";
            if (studentsMap.containsKey(login)) return "student";
        }
        return null;
    }

    public static Teacher getTeacherByLogin(String login) {
        return teachersMap.get(login);
    }

    public static Student getStudentByLogin(String login) {
        return studentsMap.get(login);
    }

    public static List<Student> getStudentsByTeacher(Teacher teacher) {
        List<Student> list = new ArrayList<>();
        for (Student student : students) {
            for (int group : student.getGroups()) {
                if (teacher.getGroupId() == group) {
                    list.add(student);
                }
            }
        }
        return list;
    }

    public static List<Admin> getAdmins() {
        return new ArrayList<>(admins);
    }

    public static List<Teacher> getTeachers() {
        return new ArrayList<>(teachers);
    }

    public static List<Student> getStudents() {
        return new ArrayList<>(students);
    }

}
