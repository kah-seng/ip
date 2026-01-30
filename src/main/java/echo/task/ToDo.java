package echo.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
