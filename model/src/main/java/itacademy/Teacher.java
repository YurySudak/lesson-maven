package itacademy;

import java.util.List;

public class Teacher extends User {
    private List<Double> salary;
    private Group group;

    public Teacher() {
        super(null, -1, null, null);
        this.salary = null;
    }

    public Teacher(String fio, int age, String login, String password, List<Double> list) {
        super(fio, age, login, password);
        this.salary = list;
    }

    public List<Double> getSalary() {
        return salary;
    }

    public void setSalary(List<Double> salary) {
        this.salary = salary;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "fio='" + fio + '\'' +
                ", salary=" + salary +
                '}';
    }
}
