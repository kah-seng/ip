package echo.command;

import echo.Ui;
import echo.task.TaskManager;

public class ListCommand extends Command {
    private TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void execute() {
        Ui.showList(this.taskManager);
    }
}
