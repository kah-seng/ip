public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
