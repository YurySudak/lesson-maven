package itacademy;

import java.util.List;

public class Teacher {
    private String name;
    private List<Double> salary;

    public Teacher() {
    }

    public Teacher(String name, List<Double> list) {
        this.name = name;
        this.salary = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getSalary() {
        return salary;
    }

    public void setSalary(List<Double> salary) {
        this.salary = salary;
    }
}