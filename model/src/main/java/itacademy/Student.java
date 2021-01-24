package itacademy;

import java.util.List;

public class Student extends User {
    private List<Integer> groups;

    public Student(int id, String fio, int age, String login, String password, List<Integer> groups) {
        super(id, 3, fio, age, login, password);
        this.groups = groups;
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }
}
