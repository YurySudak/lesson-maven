package itacademy;

import java.util.List;

public class Teacher extends User {
    private List<Double> salary;
    private int groupId;

    public Teacher() {
        super(-1, 2, null, -1, null, null);
        this.salary = null;
    }

    public Teacher(int id, String fio, int age, String login, String password, int groupId, List<Double> salary) {
        super(id, 2, fio, age, login, password);
        this.groupId = groupId;
        this.salary = salary;
    }

    public List<Double> getSalary() {
        return salary;
    }

    public void setSalary(List<Double> salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "fio='" + fio + '\'' +
                ", salary=" + salary +
                '}';
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
