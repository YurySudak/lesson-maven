package itacademy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcUtil {
    public static BigDecimal calcSalary(User teacher, int lastMonths) {
        double sum = 0;
        int amountOfMonths = RepositoryService.AMOUNT_OF_MONTHS - lastMonths;
        for (Salary salary : HibernateRepository.getSalaries()) {
            if (salary.getTeacherId() == teacher.getId()) {
                if (salary.getMonth() > amountOfMonths) {
                    sum += salary.getValue();
                }
            }
        }
        return BigDecimal.valueOf(sum / lastMonths).setScale(2, RoundingMode.HALF_DOWN);
    }
}
