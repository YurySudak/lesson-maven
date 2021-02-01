package itacademy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CalcUtilTest {

    public static final Teacher teacher1 = new Teacher("name", 30, "l", "p", List.of(400.0, 600.0));
    public static final Teacher teacher2 = new Teacher("name", 30, "l", "p", List.of(400.0));
    public static final Teacher teacher3 = new Teacher("name", 30, "l", "p", List.of(0.0));
    public static final Teacher teacher4 = new Teacher("name", 30, "l", "p", List.of(400.0, 400.0, 600.00, 600.00));

    @Test
    public void test() {
        assertEquals("500.00", CalcUtil.calcSalary(CalcUtilTest.teacher1, 2).toString());
        assertEquals("400.00", CalcUtil.calcSalary(CalcUtilTest.teacher2, 1).toString());
        assertEquals("0.00", CalcUtil.calcSalary(CalcUtilTest.teacher3, 1).toString());
        assertEquals("500.00", CalcUtil.calcSalary(CalcUtilTest.teacher4, 4).toString());
        assertEquals("400.00", CalcUtil.calcSalary(CalcUtilTest.teacher4, 2).toString());
    }
}
