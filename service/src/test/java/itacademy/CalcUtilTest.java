package itacademy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CalcUtilTest {

    public static Teacher teacher1 = new Teacher();
    public static Teacher teacher2 = new Teacher();
    public static Teacher teacher3 = new Teacher();
    public static Teacher teacher4 = new Teacher();

    @Test
    public void test() {
        teacher1.setSalary(List.of(400.0, 600.0));
        teacher2.setSalary(List.of(400.0, 0.0));
        teacher3.setSalary(List.of(0.0, 0.0));
        teacher4.setSalary(List.of(400.0, 400.0, 600.00, 600.00));

        assertEquals("500.00", CalcUtil.calcSalary(teacher1, 2).toString());
        assertEquals("400.00", CalcUtil.calcSalary(teacher2, 1).toString());
        assertEquals("0.00", CalcUtil.calcSalary(teacher3, 1).toString());
        assertEquals("500.00", CalcUtil.calcSalary(teacher4, 4).toString());
        assertEquals("400.00", CalcUtil.calcSalary(teacher4, 2).toString());
    }
}
