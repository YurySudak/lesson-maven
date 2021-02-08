package itacademy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "type")
public class Type {
    @Id
    private int id;
    @Column(name = "type_name")
    private String typeName;
}
