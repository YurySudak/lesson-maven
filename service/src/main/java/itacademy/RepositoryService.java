package itacademy;

import java.util.List;
import java.util.Set;

public class RepositoryService {

    public static final int AMOUNT_OF_THEMES = HibernateRepository.getAmountOfThemes();
    public static final int AMOUNT_OF_MONTHS = HibernateRepository.getAmountOfMonths();

    public static void addUser(User user) {
        HibernateRepository.addUser(user);
    }

    public static Group addGroup(String groupName) {
        return HibernateRepository.addGroup(groupName);
    }

    public static User getUserByLogin(String login) {
        return HibernateRepository.getUserByLogin(login);
    }

    public static Set<Mark> getMarks() {
        return HibernateRepository.getMarks();
    }

    public static void setMark(int id, int value) {
        HibernateRepository.setMark(id, value);
    }

    public static void updateMarks() {
        HibernateRepository.updateMarks();
    }

    public static List<User> getTeachers() {
        return HibernateRepository.getUsers(RepoConst.TEACHER);
    }

    public static List<User> getAdmins() {
        return HibernateRepository.getUsers(RepoConst.ADMIN);
    }

    public static List<User> getStudents() {
        return HibernateRepository.getUsers(RepoConst.STUDENT);
    }

    public static User getStudentByLogin(String login) {
        return HibernateRepository.getUserByLogin(login);
    }

    public static Mark getMark(int studentId, int groupId, int theme) {
        return HibernateRepository.getMark(studentId, groupId, theme);
    }

    public static List<User> getStudentsByTeacher(User teacher) {
        return HibernateRepository.getStudentsByTeacher(teacher, RepoConst.STUDENT);
    }

    public static void deleteUser(String login) {
        HibernateRepository.deleteUser(login);
    }

    public static void setSalary(User teacher, int month, double value) {
        HibernateRepository.setSalary(teacher, month, value);
    }

    public static String getGroupNameByTeacher(User teacher) {
        return HibernateRepository.getGroupNameByTeacher(teacher);
    }

    public static Salary getSalary(User teacher, int month) {
        return HibernateRepository.getSalary(teacher, month);
    }

    public static boolean checkLogin(String login) {
        return HibernateRepository.checkLogin(login);
    }
}
