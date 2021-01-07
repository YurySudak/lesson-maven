package itacademy;

import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    private static List<Student> students = new ArrayList<>();
    private static boolean isInit = false;

    public static void init() {
        students.add(new Student("Глод Денис Денисович", 20, "glod", "glod", List.of(GroupsRepository.getGroups().get(0), GroupsRepository.getGroups().get(1))));
        students.add(new Student("Тихонович Евгений Евгеньевич", 21, "tih", "tih", List.of(GroupsRepository.getGroups().get(0), GroupsRepository.getGroups().get(1))));
        students.add(new Student("Лапунька Иван Иванович", 20, "lap", "lap", List.of(GroupsRepository.getGroups().get(0), GroupsRepository.getGroups().get(2))));
        students.add(new Student("Костюкевич Александр Александрович", 21, "kos", "kos", List.of(GroupsRepository.getGroups().get(0), GroupsRepository.getGroups().get(2))));
        students.add(new Student("Шашко Анна Анатольевна", 21, "sha", "sha", List.of(GroupsRepository.getGroups().get(0))));
        students.add(new Student("Корчевский Павел Павлович", 22, "kor", "kor", List.of(GroupsRepository.getGroups().get(0))));
    }

    public static List<Student> getStudents() {
        if (!isInit) {
            init();
            isInit = false;
        }
        return students;
    }
}
