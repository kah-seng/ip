package echo.command;

public abstract class Command {
    /**
     * Returns if the command is signaling for the program to terminate.
     * By default, a command does not.
     * @return Boolean of whether the program should terminate.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     */
    public abstract String execute();
}
