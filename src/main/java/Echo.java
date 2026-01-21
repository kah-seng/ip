import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        Message.welcome();

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            String userInput = scanner.nextLine();
            String[] splitUserInput = userInput.split(" ");
            String command = splitUserInput.length > 0 ? splitUserInput[0] : "";

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                taskManager.list();
            } else if (command.equals("mark") || command.equals("unmark")) {
                markUnmarkHandler(command, splitUserInput, taskManager);
            } else if (command.equals("deadline")) {
                deadlineHandler(userInput, taskManager);
            } else if (command.equals("event")) {
                eventHandler(userInput, taskManager);
            } else if (command.equals("todo")) {
                toDoHandler(splitUserInput, userInput, taskManager);
            } else if (command.equals("delete")) {
                deleteHandler(splitUserInput, taskManager);
            } else {
                Message.invalidCommand();
            }
        }

        Message.bye();
    }

    private static void markUnmarkHandler(String command, String[] splitUserInput, TaskManager taskManager) {
        if (splitUserInput.length != 2) {
            Message.invalidArguments(command);
            return;
        }

        try {
            if (command.equals("mark")) {
                taskManager.markTask(splitUserInput[1]);
            } else {
                taskManager.unmarkTask(splitUserInput[1]);
            }
        } catch (InvalidTaskNumberException e) {
            Message.invalidTaskNumber(taskManager);
        }
    }

    private static void deadlineHandler(String userInput, TaskManager taskManager) {
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
    }

    private static void eventHandler(String userInput, TaskManager taskManager) {
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
    }

    private static void toDoHandler(String[] splitUserInput, String userInput, TaskManager taskManager) {
        if (splitUserInput.length < 2) {
            Message.invalidArguments("todo");
        } else {
            ToDo toDo = new ToDo(userInput.substring(5));
            taskManager.addTask(toDo);
        }
    }

    private static void deleteHandler(String[] splitUserInput, TaskManager taskManager) {
        if (splitUserInput.length != 2) {
            Message.invalidArguments("delete");
            return;
        }

        try {
            taskManager.deleteTask(splitUserInput[1]);
        } catch (InvalidTaskNumberException e) {
            Message.invalidTaskNumber(taskManager);
        }
    }
}