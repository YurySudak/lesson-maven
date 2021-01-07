package itacademy;

import java.util.ArrayList;
import java.util.List;

public class TeachersRepository {
    private static List<Teacher> teachers = new ArrayList<>();
    private static boolean isInit = false;

    public static void init() {
        teachers.add(new Teacher("Слабко Юлий Юльевич", 36, "slab", "slab", List.of(1400.0, 1400.0, 1400.0, 1400.0, 1600.0, 1600.0, 1600.0, 1600.0), GroupsRepository.getGroups().get(0)));
        teachers.add(new Teacher("Веремкович Александр Александрович", 37,"ver", "ver", List.of(1500.0, 1500.0, 1500.0, 1500.0, 1600.0, 1600.0, 1600.0, 1600.0), GroupsRepository.getGroups().get(1)));
        teachers.add(new Teacher("Перевозников Артем Артемович", 38,"per", "per", List.of(1600.0, 1600.0, 1600.0, 1600.0, 1800.0, 1800.0, 1800.0, 1800.0), GroupsRepository.getGroups().get(2)));
    }

    public static List<Teacher> getTeachers() {
        if (!isInit) {
            init();
            isInit = true;
        }
        return teachers;
    }
}
