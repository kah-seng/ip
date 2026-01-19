import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    public void markTask(int taskNumber) {
        this.tasks.get(taskNumber - 1).setIsDone(true);
    }

    public void unmarkTask(int taskNumber) {
        this.tasks.get(taskNumber - 1).setIsDone(false);
    }

    public boolean checkTaskNumber(String taskNumber) {
        try {
            int taskNumberInt = Integer.parseInt(taskNumber);
            return taskNumberInt >= 1 && taskNumberInt <= this.tasks.size();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            sb.append(String.format("\n%d. %s", i + 1, task));
        }
        return sb.toString();
    }
}
