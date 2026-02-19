package echo;

import echo.task.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path path;

    public Storage(Path path) throws IOException {
        this.path = path;
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            // This catch clause is empty as this exception is only raised if the data file already exists.
            // No further action needs to be taken as the chatbot should just read from this file subsequently.
        }
    }

    /**
     * Returns a TaskManager instance from the text file in the specified path.
     * @return TaskManager instance.
     * @throws IOException If attempting to access the text file was not successful.
     */
    public TaskManager createTaskManager() throws IOException {
        Scanner scanner = new Scanner(this.path);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Parse common fields of all task types
            char taskType;
            boolean isDone;
            int taskTypeIndex = line.indexOf('[');
            int isDoneIndex = line.indexOf('[', taskTypeIndex + 1);
            if (taskTypeIndex == -1 || taskTypeIndex >= line.length() - 3 || line.charAt(taskTypeIndex + 2) != ']'
                    || isDoneIndex == -1 || isDoneIndex >= line.length() - 3 || line.charAt(isDoneIndex + 2) != ']') {
                // Task type or marked flag format is invalid
                continue;
            } else {
                // Parse task type
                taskType = line.charAt(taskTypeIndex + 1);
                if (taskType != 'T' && taskType != 'E' && taskType != 'D') {
                    continue;
                }
                // Parse marked flag
                if (line.charAt(isDoneIndex + 1) != ' ' && line.charAt(isDoneIndex + 1) != 'X') {
                    continue;
                }
                isDone = line.charAt(isDoneIndex + 1) == 'X';
            }

            if (taskType == 'T') {
                parseToDo(line, isDoneIndex, isDone, tasks);
            } else if (taskType == 'E') {
                parseEvent(line, isDoneIndex, isDone, tasks);
            } else {
                parseDeadline(line, isDoneIndex, isDone, tasks);
            }
        }

        return new TaskManager(tasks);
    }

    private void parseToDo(String line, int isDoneIndex, boolean isDone, ArrayList<Task> tasks) {
        Task task = new ToDo(line.substring(isDoneIndex + 4));
        task.setIsDone(isDone);
        tasks.add(task);
    }

    private void parseEvent(String line, int isDoneIndex, boolean isDone, ArrayList<Task> tasks) {
        String taskName = getTaskName(line, isDoneIndex);
        if (taskName == null) {
            return;
        }

        try {
            int fromIndex = line.indexOf("from: ");
            int toIndex = line.indexOf("to: ");
            LocalDate from = LocalDate.parse(line.substring(fromIndex + 6, toIndex - 2));
            LocalDate to = LocalDate.parse(line.substring(toIndex + 4, line.length() - 1));
            Task task = new Event(taskName, from, to);
            task.setIsDone(isDone);
            tasks.add(task);
        } catch (DateTimeParseException e) {
            return;
        }
    }

    private void parseDeadline(String line, int isDoneIndex, boolean isDone, ArrayList<Task> tasks) {
        String taskName = getTaskName(line, isDoneIndex);
        if (taskName == null) {
            return;
        }

        try {
            int byIndex = line.indexOf("by: ");
            LocalDate by = LocalDate.parse(line.substring(byIndex + 4, line.length() - 1));
            Task task = new Deadline(taskName, by);
            task.setIsDone(isDone);
            tasks.add(task);
        } catch (DateTimeParseException e) {
            return;
        }
    }

    private String getTaskName(String line, int isDoneIndex) {
        int dateTimeIndex = line.indexOf('(');
        if (dateTimeIndex == -1 || line.substring(isDoneIndex + 1, dateTimeIndex - 1).length() <= 0) {
            return null;
        }
        return line.substring(isDoneIndex + 4, dateTimeIndex - 1);
    }

    /**
     * Writes the current list of tasks to the text file at the specified path.
     * @param tasks List of current tasks.
     * @throws IOException If attempting to access the text file was not successful.
     */
    public void saveToFile(ArrayList<Task> tasks) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(String.format("%d. %s\n", i + 1, task.toSaveString()));
        }
        Files.writeString(path, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }
}
