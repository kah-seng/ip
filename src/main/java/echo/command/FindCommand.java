package echo.command;

import echo.Ui;
import echo.task.TaskManager;

public class FindCommand extends Command {
    private String userInput;
    private TaskManager taskManager;

    public FindCommand(String userInput, TaskManager taskManager) {
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    @Override
    public void execute() {
        String searchString = this.userInput.substring(5).trim();
        if (searchString.length() <= 0) {
            Ui.showInvalidArgumentsWarning("find");
        } else {
            TaskManager filteredTaskManager = this.taskManager.filter(searchString);
            Ui.showFind(filteredTaskManager);
        }
    }
}
