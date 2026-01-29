import echo.exception.InvalidTaskNumberException;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Storage storage) {
        this.tasks.add(task);
        Message.taskAdded(task.toString());

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Message.fileError();
        }
    }

    public void deleteTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        Message.taskDeleted(this.tasks.get(taskNumber - 1).toString());
        this.tasks.remove(taskNumber - 1);

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Message.fileError();
        }
    }

    public void markTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        this.tasks.get(taskNumber - 1).setIsDone(true);
        Message.taskMarked(this.tasks.get(taskNumber - 1).toString());

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Message.fileError();
        }
    }

    public void unmarkTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        this.tasks.get(taskNumber - 1).setIsDone(false);
        Message.taskUnmarked(this.tasks.get(taskNumber - 1).toString());

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Message.fileError();
        }
    }

    public int parseTaskNumber(String taskNumber) {
        try {
            int taskNumberInt = Integer.parseInt(taskNumber);
            return taskNumberInt >= 1 && taskNumberInt <= this.tasks.size() ? taskNumberInt : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void list() {
        Message.list(this);
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
