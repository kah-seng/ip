package echo.command;

import echo.Ui;

public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     * The Bye command signals to the program to terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Ui.showBye();
    }
}
