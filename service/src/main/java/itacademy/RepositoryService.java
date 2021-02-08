package itacademy;

import java.util.List;

public class RepositoryService {

    public static final int AMOUNT_OF_THEMES = HibernateRepository.getAmountOfThemes();

    public static void addUser(User user) throws ExistException {
        HibernateRepository.addUser(user);
    }

    public static void addGroup(String groupName) throws ExistException {
        HibernateRepository.addGroup(groupName);
    }

    public static User getUserByLogin(String login) {
        return HibernateRepository.getUserByLogin(login);
    }

    public static List<Mark> getMarks() {
        return HibernateRepository.getMarks();
    }

    public static void setMark(int id, int value) {
        HibernateRepository.setMark(id, value);
    }

    public static int updateMarks() {
        return 0;
    }

    public static List<User> getTeachers() {
        return null;
    }

    public static void init() {

    }

    public static List<User> getAdmins() {
        return null;
    }

    public static List<User> getStudents() {
        return null;
    }

    public static User getStudentByLogin(String login) {
        return null;
    }

    public static String getGroupNameById(Integer groupId) {
        return null;
    }

    public static Mark getMark(int id, int groupId, int theme) {
        return null;
    }

    public static User getTeacherByLogin(String login) {
        return null;
    }

    public static List<User> getStudentsByTeacher(User teacher) {
        return null;
    }

    public static void deleteTeacher(String login) {

    }

    public static void setSalary(User teacher, List<Double> list) {

    }
}
