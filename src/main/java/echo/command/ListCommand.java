package echo.command;

import echo.task.TaskManager;

public class ListCommand extends Command {
    private TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void execute() {
        this.taskManager.list();
    }
}
