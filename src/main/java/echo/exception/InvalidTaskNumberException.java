package echo.exception;

public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException() {
        super("Invalid task number");
    }
}
