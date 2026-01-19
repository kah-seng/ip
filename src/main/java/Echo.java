import java.util.ArrayList;
import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        System.out.print(Message.getWelcomeMessage());

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.print(Message.getListMessage(taskManager));
            } else {
                taskManager.addTask(new Task(userInput));
                System.out.print(Message.getAddedMessage(userInput));
            }
        }

        System.out.print(Message.getByeMessage());
    }
}
