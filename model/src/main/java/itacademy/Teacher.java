package itacademy;

import java.util.List;

public class Teacher extends User {
    private List<Double> salary;

    public Teacher() {
    }

    public Teacher(String fio, List<Double> list) {
        this.fio = fio;
        this.salary = list;
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
}
