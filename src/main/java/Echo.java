import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        System.out.print(Message.getWelcomeMessage());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {

            } else {
                System.out.print(Message.getAddedMessage(userInput));
            }
        }

        System.out.print(Message.getByeMessage());
    }
}
