package echo;

import echo.task.TaskManager;

public class Ui {
    public static MainWindow mainWindow;

    /**
     * Warns user that a line in the saved text file is invalid.
     * @param line A single line in the text file.
     */
    public static void showLineWarning(String line) {
        mainWindow.showMessage("WARNING: Could not parse line \"" + line + "\"");
    }

    /**
     * Warns user that the program failed to access the saved text file.
     */
    public static void showFileWarning() {
        mainWindow.showMessage("WARNING: Could not access ./data/Echo.txt");
    }

    /**
     * Shows success message upon adding a task.
     * @param toAdd Description of the new task.
     */
    public static void showTaskAdded(String toAdd) {
        mainWindow.showMessage("ADDED: " + toAdd);
    }

    /**
     * Shows success message upon deleting a task.
     * @param toRemove Description of the task to delete.
     */
    public static void showTaskDeleted(String toRemove) {
        mainWindow.showMessage("REMOVED: " + toRemove);
    }

    /**
     * Lists all the user's current saved tasks.
     * @param taskManager TaskManager instance to list the tasks from.
     */
    public static void showList(TaskManager taskManager) {
        mainWindow.showMessage("Here are the tasks in your list:" + taskManager.toString());
    }

    public static void showFind(TaskManager taskManager) {
        mainWindow.showMessage("Here are the matching tasks in your list:" + taskManager.toString());
    }

    /**
     * Warns user that the specified task number does not exist.
     * @param taskManager TaskManager instance to generate the valid task numbers from.
     */
    public static void showInvalidTaskNumberWarning(TaskManager taskManager) {
        if (taskManager.getSize() > 1) {
            mainWindow.showMessage(
                    "WARNING: Task number should be from 1 to " + taskManager.getSize());
        } else if (taskManager.getSize() == 1) {
            mainWindow.showMessage("WARNING: Task number can only be 1");
        } else {
            mainWindow.showMessage("WARNING: No tasks added yet");
        }
    }

    /**
     * Shows success message upon marking a task as done.
     * @param taskString Description of the task to be marked as done.
     */
    public static void showTaskMarked(String taskString) {
        mainWindow.showMessage("MARKED: " + taskString);
    }

    /**
     * Shows success message upon unmarking a task.
     * @param taskString Description of the task to be unmarked.
     */
    public static void showTaskUnmarked(String taskString) {
        mainWindow.showMessage("UNMARKED: " + taskString);
    }

    /**
     * Shows welcome message.
     */
    public static void showWelcome() {
        mainWindow.showMessage("Hello! I'm Echo.\nWhat can I do for you?");
    }

    /**
     * Shows bye message.
     */
    public static void showBye() {
        mainWindow.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Shows warning when user types an invalid command.
     */
    public static void showInvalidCommandWarning() {
        mainWindow.showMessage("WARNING: Command not recognized, please try again");
    }

    /**
     * Shows warning when user formats a command wrongly.
     * @param command Command that user attemped to use.
     */
    public static void showInvalidArgumentsWarning(String command) {
        if (command.equals("mark")) {
            mainWindow.showMessage(
                    "WARNING: Invalid/Missing argument\nUSAGE: mark [task number]");
        } else if (command.equals("unmark")) {
            mainWindow.showMessage(
                    "WARNING: Invalid/Missing argument\nUSAGE: unmark [task number]");
        } else if (command.equals("delete")) {
            mainWindow.showMessage(
                    "WARNING: Invalid/Missing argument\nUSAGE: delete [task number]");
        } else if (command.equals("todo")) {
            mainWindow.showMessage(
                    "WARNING: Invalid/Missing argument\nUSAGE: todo [name/description of todo]");
        } else if (command.equals("event")) {
            mainWindow.showMessage(
                    "WARNING: Invalid/Missing argument(s)\nUSAGE: event [name/description of event] /from [date] " +
                            "/to [date]");
        } else if (command.equals("deadline")) {
            mainWindow.showMessage(
                    "WARNING: Invalid/Missing argument\nUSAGE: deadline [name/description of deadline] /by [date]");
        } else if (command.equals("find")) {
            mainWindow.showMessage("WARNING: Invalid/Missing argument\nUSAGE: find [keyword(s)]");
        }
    }
}
