public class Message {
    public static final String DIVIDER = "------------------------------------------------------------";

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
        System.out.print(Message.wrapInDividers("ERROR: Task number should be from 1 to " + taskManager.getSize()));
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

    private static String wrapInDividers(String msg) {
        return String.format("""
                
                %s
                %s
                %s
                
                """, Message.DIVIDER, msg, Message.DIVIDER);
    }
}
