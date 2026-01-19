import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        System.out.println("""
                ------------------------------------------------------------
                Hello! I'm Echo.
                What can I do for you?
                ------------------------------------------------------------
                """);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println("\n------------------------------------------------------------\n"
                    + userInput + "\n"
                    + "------------------------------------------------------------\n");
        }

        System.out.println("""
                \n------------------------------------------------------------
                Bye. Hope to see you again soon!
                ------------------------------------------------------------
                """);
    }
}
