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
            } else if (splitUserInput[0].equals("deadline")) {
                int byIndex = -1;
                for (int i = 0; i < splitUserInput.length; i++) {
                    if (splitUserInput[i].equals("/by")) {
                        byIndex = i;
                        break;
                    }
                }

                if (byIndex < 0) {
                    // TODO
                } else {
                    StringBuilder deadlineNameBuilder = new StringBuilder();
                    for (int i = 1; i < byIndex; i++) {
                        deadlineNameBuilder.append(splitUserInput[i]).append(" ");
                    }
                    // Remove trailing space
                    deadlineNameBuilder.deleteCharAt(deadlineNameBuilder.length() - 1);

                    StringBuilder deadlineByBuilder = new StringBuilder();
                    for (int i = byIndex + 1; i < splitUserInput.length; i++) {
                        deadlineByBuilder.append(splitUserInput[i]).append(" ");
                    }
                    // Remove trailing space
                    deadlineByBuilder.deleteCharAt(deadlineByBuilder.length() - 1);

                    Deadline deadline = new Deadline(deadlineNameBuilder.toString(), deadlineByBuilder.toString());
                    taskManager.addTask(deadline);
                    System.out.print(Message.getAddedMessage(deadline.toString()));
                }
            }
        }

        System.out.print(Message.getByeMessage());
    }
}
