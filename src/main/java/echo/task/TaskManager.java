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

    public String addTask(Task task, Storage storage) {
        try {
            this.tasks.add(task);
            storage.saveToFile(this.tasks);
            return Ui.getTaskAdded(task.toString());
        } catch (IOException e) {
            return Ui.getFileWarning();
        }
    }

    public String deleteTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        try {
            this.tasks.remove(taskNumber - 1);
            storage.saveToFile(this.tasks);
            return Ui.getTaskDeleted(this.tasks.get(taskNumber - 1).toString());
        } catch (IOException e) {
            return Ui.getFileWarning();
        }
    }

    public String markTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }

        try {
            this.tasks.get(taskNumber - 1).setIsDone(true);
            storage.saveToFile(this.tasks);
            return Ui.getTaskMarked(this.tasks.get(taskNumber - 1).toString());
        } catch (IOException e) {
            return Ui.getFileWarning();
        }
    }

    public String unmarkTask(String taskNumberString, Storage storage) throws InvalidTaskNumberException {
        int taskNumber = this.parseTaskNumber(taskNumberString);

        if (taskNumber < 0) {
            throw new InvalidTaskNumberException();
        }



        try {
            this.tasks.get(taskNumber - 1).setIsDone(false);
            storage.saveToFile(this.tasks);
            return Ui.getTaskUnmarked(this.tasks.get(taskNumber - 1).toString());
        } catch (IOException e) {
            return Ui.getFileWarning();
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
