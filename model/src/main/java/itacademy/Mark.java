package itacademy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "mark")
public class Mark {
    @Id
    private int id;
    @Column(name = "group_id")
    private int groupId;
    @Column(name = "student_id")
    private int studentId;
    private int theme;
    private int value;
}
