package echo.task;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toSaveString() {
        return this.toString();
    }

    public boolean isMatch(String searchString) {
        return name.contains(searchString);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.isDone ? 'X' : ' ', this.name);
    }
}
