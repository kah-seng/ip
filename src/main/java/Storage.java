import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path path;

    public Storage(Path path) {
        this.path = path;
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            Message.fileFound();
        } catch (IOException e) {
            Message.fileError();
        }
    }

    public TaskManager createTaskManager() throws IOException {
        Scanner scanner = new Scanner(this.path);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
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
                Task task = new ToDo(line.substring(isDoneIndex + 4));
                task.setIsDone(isDone);
                tasks.add(task);
                continue;
            }

            String taskName;
            int dateTimeIndex = line.indexOf('(');
            if (dateTimeIndex == -1 || line.substring(isDoneIndex + 1, dateTimeIndex - 1).length() <= 0) {
                continue;
            }
            taskName = line.substring(isDoneIndex + 4, dateTimeIndex - 1);

            if (taskType == 'E') {
                int fromIndex = line.indexOf("from: ");
                int toIndex = line.indexOf("to: ");
                String from = line.substring(fromIndex + 6, toIndex - 2);
                String to = line.substring(toIndex + 4, line.length() - 1);
                Task task = new Event(taskName, from, to);
                task.setIsDone(isDone);
                tasks.add(task);
            } else {
                int byIndex = line.indexOf("by: ");
                String by = line.substring(byIndex + 4, line.length() - 1);
                Task task = new Deadline(taskName, by);
                task.setIsDone(isDone);
                tasks.add(task);
            }
        }

        return new TaskManager(tasks);
    }

    public void saveToFile(ArrayList<Task> tasks) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(String.format("%d. %s\n", i + 1, task));
        }
        Files.writeString(path, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }
}
