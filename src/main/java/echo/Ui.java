package echo;

import echo.task.TaskManager;

public class Ui {
    public static void showLineWarning(String line) {
        System.out.println(Ui.wrapInDividers("WARNING: Could not parse line \"" + line + "\""));
    }

    public static void showFileWarning() {
        System.out.print(Ui.wrapInDividers("WARNING: Could not access ./data/Echo.txt"));
    }

    public static void showTaskAdded(String toAdd) {
        System.out.print(Ui.wrapInDividers("ADDED: " + toAdd));
    }

    public static void showTaskDeleted(String toRemove) {
        System.out.print(Ui.wrapInDividers("REMOVED: " + toRemove));
    }

    public static void showList(TaskManager taskManager) {
        System.out.print(Ui.wrapInDividers(taskManager.toString()));
    }

    public static void showInvalidTaskNumberWarning(TaskManager taskManager) {
        if (taskManager.getSize() > 1) {
            System.out.print(Ui.wrapInDividers(
                    "WARNING: Task number should be from 1 to " + taskManager.getSize()));
        } else if (taskManager.getSize() == 1) {
            System.out.print(Ui.wrapInDividers("WARNING: Task number can only be 1"));
        } else {
            System.out.print(Ui.wrapInDividers("WARNING: No tasks added yet"));
        }
    }

    public static void showTaskMarked(String taskString) {
        System.out.print(Ui.wrapInDividers("MARKED: " + taskString));
    }

    public static void showTaskUnmarked(String taskString) {
        System.out.print(Ui.wrapInDividers("UNMARKED: " + taskString));
    }

    public static void showWelcome() {
        System.out.print(Ui.wrapInDividers("Hello! I'm Echo.\nWhat can I do for you?"));
    }

    public static void showBye() {
        System.out.print(Ui.wrapInDividers("Bye. Hope to see you again soon!"));
    }

    public static void showInvalidCommandWarning() {
        System.out.print(Ui.wrapInDividers("WARNING: Command not recognized, please try again"));
    }

    public static void showInvalidArgumentsWarning(String command) {
        if (command.equals("mark")) {
            System.out.print(Ui.wrapInDividers(
                    "WARNING: Invalid/Missing argument\nUSAGE: mark [task number]"));
        } else if (command.equals("unmark")) {
            System.out.print(Ui.wrapInDividers(
                    "WARNING: Invalid/Missing argument\nUSAGE: unmark [task number]"));
        } else if (command.equals("delete")) {
            System.out.print(Ui.wrapInDividers(
                    "WARNING: Invalid/Missing argument\nUSAGE: delete [task number]"));
        } else if (command.equals("todo")) {
            System.out.print(Ui.wrapInDividers(
                    "WARNING: Invalid/Missing argument\nUSAGE: todo [name/description of todo]"));
        } else if (command.equals("event")) {
            System.out.print(Ui.wrapInDividers(
                    "WARNING: Invalid/Missing argument(s)\nUSAGE: event [name/description of event] /from [date] " +
                            "/to [date]"));
        } else if (command.equals("deadline")) {
            System.out.print(Ui.wrapInDividers(
                    "WARNING: Invalid/Missing argument\nUSAGE: deadline [name/description of deadline] /by [date]"));
        }
    }

    public static String wrapInDividers(String msg) {
        String divider = "------------------------------------------------------------";
        return String.format("%s\n%s\n%s\n", divider, msg, divider);
    }
}
