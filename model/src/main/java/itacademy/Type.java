package itacademy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity(name = "type")
public class Type  implements Serializable {
    @Id
    private int id;
    @Column(name = "type_name")
    private String typeName;
}
