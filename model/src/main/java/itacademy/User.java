package itacademy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "user_")
public class User {
    @Id
    private int id;
    private int type;
    private String fio;
    private int age;
    private String login;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login);
    }
}
