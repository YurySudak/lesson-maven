package itacademy;

import java.util.List;

public class RepositoryService {

    public static int amountOfThemes = MarksRepository.amountOfThemes;

    public static void addUser(Teacher teacher) {
        UsersRepository.addUser(teacher);
    }

    public static int addGroup(String groupName) {
        return Db.addGroup(groupName);
    }

    public static void setGroup(int groupId, String groupName) {
        NamesRepository.setGroup(groupId, groupName);
    }

    public static User getUserByLogin(String login) {
        return UsersRepository.getUserByLogin(login);
    }

    public static List<Mark> getMarks() {
        return MarksRepository.getMarks();
    }

    public static void setMark(int id, int value) {
        MarksRepository.setMark(id, value);
    }

    public static int updateMarks() {
        return Db.updateMarks();
    }

    public static List<Teacher> getTeachers() {
        return UsersRepository.getTeachers();
    }

    public static void init() {
        UsersRepository.init();
        MarksRepository.init();
        NamesRepository.init();
    }

    public static List<Admin> getAdmins() {
        return UsersRepository.getAdmins();
    }

    public static List<Student> getStudents() {
        return UsersRepository.getStudents();
    }

    public static Student getStudentByLogin(String login) {
        return UsersRepository.getStudentByLogin(login);
    }

    public static String getGroupNameById(Integer groupId) {
        return NamesRepository.getGroupNameById(groupId);
    }

    public static Mark getMark(int id, int groupId, int theme) {
        return MarksRepository.getMark(id, groupId, theme);
    }

    public static Teacher getTeacherByLogin(String login) {
        return UsersRepository.getTeacherByLogin(login);
    }

    public static List<Student> getStudentsByTeacher(Teacher teacher) {
        return UsersRepository.getStudentsByTeacher(teacher);
    }
}
