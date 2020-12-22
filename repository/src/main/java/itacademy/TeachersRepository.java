package itacademy;

import java.util.ArrayList;
import java.util.List;

public class TeachersRepository {
    private static List<Teacher> teachers = new ArrayList<>();
    private static boolean isInit = false;

    public static void init() {
        teachers.add(new Teacher("Слабко Юрий Юрьевич", List.of(1400.0, 1400.0, 1400.0, 1400.0, 1600.0, 1600.0, 1600.0, 1600.0)));
        teachers.add(new Teacher("Веремкович Александр Александрович", List.of(1500.0, 1500.0, 1500.0, 1500.0, 1600.0, 1600.0, 1600.0, 1600.0)));
        teachers.add(new Teacher("Перевозников Артем Артемович", List.of(1600.0, 1600.0, 1600.0, 1600.0, 1800.0, 1800.0, 1800.0, 1800.0)));
    }

    public static List<Teacher> getTeachers() {
        if (!isInit) {
            init();
            isInit = true;
        }
        return teachers;
    }

    public static void setTeachers(List<Teacher> teachers) {
        TeachersRepository.teachers = teachers;
    }
}
