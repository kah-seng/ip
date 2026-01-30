package echo;

import echo.task.TaskManager;

public class Ui {
    /**
     * Warns user that a line in the saved text file is invalid.
     * @param line A single line in the text file.
     */
    public static void showLineWarning(String line) {
        System.out.println(Ui.wrapInDividers("WARNING: Could not parse line \"" + line + "\""));
    }

    /**
     * Warns user that the program failed to access the saved text file.
     */
    public static void showFileWarning() {
        System.out.print(Ui.wrapInDividers("WARNING: Could not access ./data/Echo.txt"));
    }

    /**
     * Shows success message upon adding a task.
     * @param toAdd Description of the new task.
     */
    public static void showTaskAdded(String toAdd) {
        System.out.print(Ui.wrapInDividers("ADDED: " + toAdd));
    }

    /**
     * Shows success message upon deleting a task.
     * @param toRemove Description of the task to delete.
     */
    public static void showTaskDeleted(String toRemove) {
        System.out.print(Ui.wrapInDividers("REMOVED: " + toRemove));
    }

    /**
     * Lists all the user's current saved tasks.
     * @param taskManager TaskManager instance to list the tasks from.
     */
    public static void showList(TaskManager taskManager) {
        System.out.print(Ui.wrapInDividers("Here are the tasks in your list:" + taskManager.toString()));
    }

    public static void showFind(TaskManager taskManager) {
        System.out.print(Ui.wrapInDividers("Here are the matching tasks in your list:" + taskManager.toString()));
    }

    /**
     * Warns user that the specified task number does not exist.
     * @param taskManager TaskManager instance to generate the valid task numbers from.
     */
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

    /**
     * Shows success message upon marking a task as done.
     * @param taskString Description of the task to be marked as done.
     */
    public static void showTaskMarked(String taskString) {
        System.out.print(Ui.wrapInDividers("MARKED: " + taskString));
    }

    /**
     * Shows success message upon unmarking a task.
     * @param taskString Description of the task to be unmarked.
     */
    public static void showTaskUnmarked(String taskString) {
        System.out.print(Ui.wrapInDividers("UNMARKED: " + taskString));
    }

    /**
     * Shows welcome message.
     */
    public static void showWelcome() {
        System.out.print(Ui.wrapInDividers("Hello! I'm Echo.\nWhat can I do for you?"));
    }

    /**
     * Shows bye message.
     */
    public static void showBye() {
        System.out.print(Ui.wrapInDividers("Bye. Hope to see you again soon!"));
    }

    /**
     * Shows warning when user types an invalid command.
     */
    public static void showInvalidCommandWarning() {
        System.out.print(Ui.wrapInDividers("WARNING: Command not recognized, please try again"));
    }

    /**
     * Shows warning when user formats a command wrongly.
     * @param command Command that user attemped to use.
     */
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
        } else if (command.equals("find")) {
            System.out.print(Ui.wrapInDividers("WARNING: Invalid/Missing argument\nUSAGE: find [keyword(s)]"));
        }
    }

    /**
     * Wraps a message in between horizontal dividers.
     * @param msg Message to be wrapped.
     * @return Wrapped message.
     */
    public static String wrapInDividers(String msg) {
        String divider = "------------------------------------------------------------";
        return String.format("%s\n%s\n%s\n", divider, msg, divider);
    }
}
