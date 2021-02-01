package itacademy;

public abstract class User {
    protected String fio;
    protected int age;
    protected String login;
    protected String password;

    public User(String fio, int age, String login, String password) {
        this.fio = fio;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
