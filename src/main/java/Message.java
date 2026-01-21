public class Message {
    public static final String DIVIDER = "------------------------------------------------------------";

    public static String getAddedMessage(String toAdd) {
        return Message.wrapInDividers("ADDED: " + toAdd);
    }

    public static String getRemovedMessage(String toRemove) {
        return Message.wrapInDividers("REMOVED: " + toRemove);
    }

    public static String getListMessage(TaskManager taskManager) {
        return Message.wrapInDividers(taskManager.toString());
    }

    public static String getInvalidTaskNumberMessage(TaskManager taskManager) {
        return Message.wrapInDividers("ERROR: Task number should be from 1 to " + taskManager.getSize());
    }

    public static String getTaskMarkedMessage(Task task) {
        return Message.wrapInDividers("MARKED: " + task);
    }

    public static String getTaskUnmarkedMessage(Task task) {
        return Message.wrapInDividers("UNMARKED: " + task);
    }

    public static String getWelcomeMessage() {
        return Message.wrapInDividers("Hello! I'm Echo.\nWhat can I do for you?");
    }

    public static String getByeMessage() {
        return Message.wrapInDividers("Bye. Hope to see you again soon!");
    }

    public static String wrapInDividers(String msg) {
        return String.format("""
                
                %s
                %s
                %s
                
                """, Message.DIVIDER, msg, Message.DIVIDER);
    }
}
