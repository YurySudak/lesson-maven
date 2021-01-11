package itacademy;

import java.util.List;

public class Teacher extends User {
    private List<Double> salary;
    private int group;

    public Teacher() {
        super(-1,null, -1, null, null);
        this.salary = null;
    }

    public Teacher(int id, String fio, int age, String login, String password, int group) {
        super(id, fio, age, login, password);
        this.group = group;
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

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
