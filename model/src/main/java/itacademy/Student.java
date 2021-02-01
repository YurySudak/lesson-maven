package itacademy;

import java.util.List;
import java.util.Map;

public class Student extends User {
    private List<Group> groups;
    private List<Map<String, String>> marks;

    public Student(String fio, int age, String login, String password, List<Group> groups) {
        super(fio, age, login, password);
        this.groups = groups;
    }
}
