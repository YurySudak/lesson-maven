package itacademy;

import java.util.ArrayList;
import java.util.List;

public class GroupsRepository {
    private static List<Group> groups = new ArrayList<>();
    private static boolean isInit = false;

    public static void init() {
        groups.add(new Group("Java Core", List.of("Intellij IDEA", "Типы данных", "Операторы", "Циклы", "Массивы", "Объекты", "Строки", "Дженерики")));
        groups.add(new Group("Java Enterprise", List.of("Maven", "HTTP", "Tomcat", "Servlets", "JSP", "Git", "SQL", "JDBC", "DAO", "Hibernate")));
        groups.add(new Group("Android", List.of("Kotlin", "Activity", "SQL", "Services and Jobs", "REST")));
    }

    public static List<Group> getGroups() {
        if (!isInit) {
            init();
            isInit = true;
        }
        return groups;
    }
}
