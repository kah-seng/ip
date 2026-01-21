import java.util.Arrays;
import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        System.out.print(Message.getWelcomeMessage());

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        while (true) {
            String userInput = scanner.nextLine();
            String[] splitUserInput = userInput.split(" ");

            if (splitUserInput.length == 1 && userInput.equals("bye")) {
                break;
            } else if (splitUserInput.length == 1 && userInput.equals("list")) {
                System.out.print(Message.getListMessage(taskManager));
            } else if (splitUserInput.length == 2
                    && (splitUserInput[0].equals("mark") || splitUserInput[0].equals("unmark"))) {
                String taskNumber = splitUserInput[1];
                if (taskManager.checkTaskNumber(taskNumber)) {
                    int taskNumberInt = Integer.parseInt(taskNumber);
                    if (splitUserInput[0].equals("mark")) {
                        taskManager.markTask(taskNumberInt);
                        System.out.print(Message.getTaskMarkedMessage(taskManager.getTask(taskNumberInt)));
                    } else {
                        taskManager.unmarkTask(taskNumberInt);
                        System.out.print(Message.getTaskUnmarkedMessage(taskManager.getTask(taskNumberInt)));
                    }
                } else {
                    System.out.print(Message.getInvalidTaskNumberMessage(taskManager));
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
                    System.out.print(Message.getAddedMessage(deadline.toString()));
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
                    System.out.print(Message.getAddedMessage(event.toString()));
                }
            } else if (userInput.startsWith("todo ")) {
                ToDo toDo = new ToDo(userInput.substring(5));
                taskManager.addTask(toDo);
                System.out.print(Message.getAddedMessage(toDo.toString()));
            }
        }

        System.out.print(Message.getByeMessage());
    }
}