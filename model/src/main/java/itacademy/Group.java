package itacademy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "group_")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "group_name")
    private String groupName;
    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    public Set<User> users = new HashSet<>();

    public Group(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupName.equals(group.groupName);
    }
}
