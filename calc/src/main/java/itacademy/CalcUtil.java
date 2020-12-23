package itacademy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcUtil {
    public static BigDecimal calcSalary(Employee employee, int amountOfMonths) {
        double sum = employee.getSalary().stream()
                .limit(amountOfMonths).mapToDouble(x -> x).sum();
        return BigDecimal.valueOf(sum / amountOfMonths).setScale(2, RoundingMode.HALF_DOWN);
    }
}
