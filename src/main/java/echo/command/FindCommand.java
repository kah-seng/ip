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
    public String execute() {
        String searchString = this.userInput.substring(5).trim();
        String[] splitSearchString = searchString.split(" ");
        if (searchString.length() <= 0) {
            return Ui.getInvalidArgumentsWarning("find");
        } else {
            TaskManager filteredTaskManager = this.taskManager.filter(splitSearchString);
            return Ui.getFind(filteredTaskManager);
        }
    }
}
