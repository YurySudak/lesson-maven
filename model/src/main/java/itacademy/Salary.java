package itacademy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity(name = "salary")
public class Salary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "teacher_id")
    private int teacherId;
    private double value;
    private int month;

    public Salary(int teacherId, int month, double value) {
        this.teacherId = teacherId;
        this.value = value;
        this.month = month;
    }
}
