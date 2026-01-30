package echo.command;

import echo.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute() {
        Ui.showInvalidCommandError();
    }
}
