package echo.task;

import echo.Storage;
import echo.Ui;
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
        Ui.showTaskAdded(task.toString());

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Ui.showFileWarning();
        }
    }

    public void deleteTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        Ui.showTaskDeleted(this.tasks.get(taskNumber - 1).toString());
        this.tasks.remove(taskNumber - 1);

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Ui.showFileWarning();
        }
    }

    public void markTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        this.tasks.get(taskNumber - 1).setIsDone(true);
        Ui.showTaskMarked(this.tasks.get(taskNumber - 1).toString());

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Ui.showFileWarning();
        }
    }

    public void unmarkTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        this.tasks.get(taskNumber - 1).setIsDone(false);
        Ui.showTaskUnmarked(this.tasks.get(taskNumber - 1).toString());

        try {
            storage.saveToFile(this.tasks);
        } catch (IOException e) {
            Ui.showFileWarning();
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

    public TaskManager filter(String searchString) {
        TaskManager filteredTaskManager = new TaskManager();
        for (Task task : this.tasks) {
            if (task.isMatch(searchString)) {
                filteredTaskManager.tasks.add(task);
            }
        }
        return filteredTaskManager;
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            sb.append(String.format("\n%d. %s", i + 1, task.toString()));
        }
        return sb.toString();
    }
}
