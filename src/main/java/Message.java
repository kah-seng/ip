public class Message {
    public static final String DIVIDER = "------------------------------------------------------------";

    public static String getAddedMessage(String toAdd) {
        return String.format("""
                
                %s
                Added: %s
                %s
                
                """, Message.DIVIDER, toAdd, Message.DIVIDER);
    }

    public static String getWelcomeMessage() {
        return String.format("""
                %s
                Hello! I'm Echo.
                What can I do for you?
                %s
                
                """, Message.DIVIDER, Message.DIVIDER);
    }

    public static String getByeMessage() {
        return String.format("""
                
                %s
                Bye. Hope to see you again soon!
                %s
                """, Message.DIVIDER, Message.DIVIDER);
    }
}
