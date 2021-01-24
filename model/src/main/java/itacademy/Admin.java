package itacademy;

public class Admin extends User {

    public Admin(int id, String fio, int age, String login, String password) {
        super(id, 1, fio, age, login, password);
    }
}
