package itacademy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "salary")
public class Salary {
    @Id
    private int id;
    @Column(name = "teacher_id")
    private int teacherId;
    private double value;
    private int month;
}
