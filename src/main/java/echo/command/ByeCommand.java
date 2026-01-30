package echo.command;

import echo.Ui;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
        Ui.showBye();
    }
}
