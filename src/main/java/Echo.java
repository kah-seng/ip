import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        Message.welcome();

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
               taskManager.list();
            } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                String[] splitUserInput = userInput.split(" ");
                String taskNumber = splitUserInput[1];
                
                if (taskManager.checkTaskNumber(taskNumber)) {
                    int taskNumberInt = Integer.parseInt(taskNumber);
                    if (splitUserInput[0].equals("mark")) {
                        taskManager.markTask(taskNumberInt);
                    } else {
                        taskManager.unmarkTask(taskNumberInt);
                    }
                } else {
                    Message.invalidTaskNumber(taskManager);
                }
            } else if (userInput.startsWith("deadline ")) {
                int byIndex = userInput.indexOf(" /by ");

                if (byIndex < 0) {
                    // TODO
                } else {
                    // Start from index 9 to remove "deadline "
                    String name = userInput.substring(9, byIndex);
                    // +5 to remove " /by "
                    String by = userInput.substring(byIndex + 5);

                    Deadline deadline = new Deadline(name, by);
                    taskManager.addTask(deadline);
                }
            } else if (userInput.startsWith("event ")) {
                int fromIndex = userInput.indexOf(" /from ");
                int toIndex = userInput.indexOf(" /to ");

                if (fromIndex < 0 || toIndex < 0) {
                    // TODO
                } else {
                    // Start from index 6 to remove "event "
                    String name = userInput.substring(6, fromIndex);
                    // +7 to remove " /from "
                    String from = userInput.substring(fromIndex + 7, toIndex);
                    // +5 to remove " /to "
                    String to = userInput.substring(toIndex + 5);

                    Event event = new Event(name, from, to);
                    taskManager.addTask(event);
                }
            } else if (userInput.startsWith("todo ")) {
                ToDo toDo = new ToDo(userInput.substring(5));
                taskManager.addTask(toDo);
            } else if (userInput.startsWith("delete ")) {
                String[] splitUserInput = userInput.split(" ");
                String taskNumber = splitUserInput[1];

                if (taskManager.checkTaskNumber(taskNumber)) {
                    int taskNumberInt = Integer.parseInt(taskNumber);
                    taskManager.deleteTask(taskNumberInt);
                } else {
                    Message.invalidTaskNumber(taskManager);
                }
            }
        }

        Message.bye();
    }
}