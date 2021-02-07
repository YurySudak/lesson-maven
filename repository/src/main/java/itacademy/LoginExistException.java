package itacademy;

public class LoginExistException extends Exception{
    public LoginExistException(String error) {
        super(error);
    }
}
