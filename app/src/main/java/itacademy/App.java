package itacademy;

import java.util.Arrays;
import java.util.List;

public class App {
    private static Employee[] employees = new Employee[] {
            new Employee("Eugene", List.of(400.0, 400.0, 400.0, 400.0, 600.0, 600.0, 600.0, 600.0)),
            new Employee("Denis", List.of(500.0, 500.0, 500.0, 500.0, 600.0, 600.0, 600.0, 600.0)),
            new Employee("Maxim", List.of(600.0, 600.0, 600.0, 600.0, 800.0, 800.0, 800.0, 800.0))
    };

    public static void main( String[] args ) {
        printAll(1);
        printAll(5);
        printAll(6);
        printAll(8);
    }

    private static void printAll(int months) {
        System.out.println("Salaries of " + months + " months: " );
        Arrays.stream(employees).forEach(e -> System.out.println("Name: " + e.getName() + ". Average salary: " + CalcUtil.calcSalary(e, months)));
    }
}
