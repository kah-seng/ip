import java.util.ArrayList;
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
            } else {
                taskManager.addTask(new Task(userInput));
                System.out.print(Message.getAddedMessage(userInput));
            }
        }

        System.out.print(Message.getByeMessage());
    }
}
