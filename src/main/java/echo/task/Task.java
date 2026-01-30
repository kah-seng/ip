package echo.task;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Marks if a task is done.
     * @param isDone New status of the task.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns formatted version to be stored in the text file.
     * @return Formatted representation of the task.
     */
    public String toSaveString() {
        return this.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", this.isDone ? 'X' : ' ', this.name);
    }
}
