package echo;

import echo.task.TaskManager;

public class Ui {
    /**
     * Warns user that a line in the saved text file is invalid.
     *
     * @param line A single line in the text file.
     */
    public static String getLineWarning(String line) {
        return "WARNING: Could not parse line \"" + line + "\"";
    }

    /**
     * Warns user that the program failed to access the saved text file.
     */
    public static String getFileWarning() {
        return "WARNING: Could not access ./data/Echo.txt";
    }

    /**
     * Shows success message upon adding a task.
     *
     * @param toAdd Description of the new task.
     */
    public static String getTaskAdded(String toAdd) {
        return "ADDED: " + toAdd;
    }

    /**
     * Shows success message upon deleting a task.
     *
     * @param toRemove Description of the task to delete.
     */
    public static String getTaskDeleted(String toRemove) {
        return "REMOVED: " + toRemove;
    }

    /**
     * Lists all the user's current saved tasks.
     *
     * @param taskManager TaskManager instance to list the tasks from.
     */
    public static String getList(TaskManager taskManager) {
        return "Here are the tasks in your list:" + taskManager.toString();
    }

    public static String getFind(TaskManager taskManager) {
        return "Here are the matching tasks in your list:" + taskManager.toString();
    }

    /**
     * Warns user that the specified task number does not exist.
     *
     * @param taskManager TaskManager instance to generate the valid task numbers from.
     */
    public static String getInvalidTaskNumberWarning(TaskManager taskManager) {
        if (taskManager.getSize() > 1) {
            return "WARNING: Task number should be from 1 to " + taskManager.getSize();
        } else if (taskManager.getSize() == 1) {
            return "WARNING: Task number can only be 1";
        } else {
            return "WARNING: No tasks added yet";
        }
    }

    /**
     * Shows success message upon marking a task as done.
     *
     * @param taskString Description of the task to be marked as done.
     */
    public static String getTaskMarked(String taskString) {
        return "MARKED: " + taskString;
    }

    /**
     * Shows success message upon unmarking a task.
     *
     * @param taskString Description of the task to be unmarked.
     */
    public static String getTaskUnmarked(String taskString) {
        return "UNMARKED: " + taskString;
    }

    /**
     * Shows welcome message.
     */
    public static String getWelcome() {
        return "Hello! I'm Echo.\nWhat can I do for you?";
    }

    /**
     * Shows bye message.
     */
    public static String getBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows warning when user types an invalid command.
     */
    public static String getInvalidCommandWarning() {
        return "WARNING: Command not recognized, please try again";
    }

    /**
     * Shows warning when user formats a command wrongly.
     *
     * @param command Command that user attemped to use.
     */
    public static String getInvalidArgumentsWarning(String command) {
        if (command.equals("mark")) {
            return "WARNING: Invalid/Missing argument\nUSAGE: mark [task number]";
        } else if (command.equals("unmark")) {
            return "WARNING: Invalid/Missing argument\nUSAGE: unmark [task number]";
        } else if (command.equals("delete")) {
            return "WARNING: Invalid/Missing argument\nUSAGE: delete [task number]";
        } else if (command.equals("todo")) {
            return "WARNING: Invalid/Missing argument\nUSAGE: todo [name/description of todo]";
        } else if (command.equals("event")) {
            return "WARNING: Invalid/Missing argument(s)\n"
                    + "USAGE: event [name/description of event] /from [date] /to [date]";
        } else if (command.equals("deadline")) {
            return "WARNING: Invalid/Missing argument\n"
                    + "USAGE: deadline [name/description of deadline] /by [date]";
        } else if (command.equals("find")) {
            return "WARNING: Invalid/Missing argument\nUSAGE: find [keyword(s)]";
        }

        return "";
    }
}
