public class Message {
    public static void taskAdded(String toAdd) {
        System.out.print(Message.wrapInDividers("ADDED: " + toAdd));
    }

    public static void taskDeleted(String toRemove) {
        System.out.print(Message.wrapInDividers("REMOVED: " + toRemove));
    }

    public static void list(TaskManager taskManager) {
        System.out.print(Message.wrapInDividers(taskManager.toString()));
    }

    public static void invalidTaskNumber(TaskManager taskManager) {
        if (taskManager.getSize() > 1) {
            System.out.print(Message.wrapInDividers(
                    "ERROR: Task number should be from 1 to " + taskManager.getSize()));
        } else if (taskManager.getSize() == 1) {
            System.out.print(Message.wrapInDividers("ERROR: Task number can only be 1"));
        } else {
            System.out.print(Message.wrapInDividers("ERROR: No tasks added yet"));
        }
    }

    public static void taskMarked(String taskString) {
        System.out.print(Message.wrapInDividers("MARKED: " + taskString));
    }

    public static void taskUnmarked(String taskString) {
        System.out.print(Message.wrapInDividers("UNMARKED: " + taskString));
    }

    public static void welcome() {
        System.out.print(Message.wrapInDividers("Hello! I'm Echo.\nWhat can I do for you?"));
    }

    public static void bye() {
        System.out.print(Message.wrapInDividers("Bye. Hope to see you again soon!"));
    }

    public static void invalidCommand() {
        System.out.print(Message.wrapInDividers("ERROR: Command not recognized, please try again"));
    }

    public static void invalidArguments(String command) {
        if (command.equals("mark")) {
            System.out.print(Message.wrapInDividers(
                    "ERROR: Invalid/Missing argument\nUSAGE: mark [task number]"));
        } else if (command.equals("unmark")) {
            System.out.print(Message.wrapInDividers(
                    "ERROR: Invalid/Missing argument\nUSAGE: unmark [task number]"));
        } else if (command.equals("delete")) {
            System.out.print(Message.wrapInDividers(
                    "ERROR: Invalid/Missing argument\nUSAGE: delete [task number]"));
        } else if (command.equals("todo")) {
            System.out.print(Message.wrapInDividers(
                    "ERROR: Invalid/Missing argument\nUSAGE: todo [name/description of todo]"));
        } else if (command.equals("event")) {
            System.out.print(Message.wrapInDividers(
                    "ERROR: Invalid/Missing argument(s)\nUSAGE: event [name/description of event] /from [date/time] " +
                            "/to [date/time]"));
        } else if (command.equals("deadline")) {
            System.out.print(Message.wrapInDividers(
                    "ERROR: Invalid/Missing argument\nUSAGE: deadline [name/description of deadline] /by [date/time]"));
        }
    }

    private static String wrapInDividers(String msg) {
        return String.format("""
                
                ------------------------------------------------------------
                %s
                ------------------------------------------------------------
                
                """, msg);
    }
}
