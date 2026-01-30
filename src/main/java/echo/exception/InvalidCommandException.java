package echo.exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command");
    }
}
