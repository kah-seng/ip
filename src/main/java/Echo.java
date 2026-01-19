import java.util.ArrayList;
import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        System.out.print(Message.getWelcomeMessage());

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userInputs = new ArrayList<>();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("\n" + Message.DIVIDER);
                for (int i = 0; i < userInputs.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, userInputs.get(i));
                }
                System.out.println(Message.DIVIDER + "\n");
            } else {
                userInputs.add(userInput);
                System.out.print(Message.getAddedMessage(userInput));
            }
        }

        System.out.print(Message.getByeMessage());
    }
}
