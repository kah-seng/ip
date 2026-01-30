package echo.command;

import echo.Storage;
import echo.Ui;
import echo.exception.InvalidTaskNumberException;
import echo.task.TaskManager;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private ArrayList<String> splitUserInput;
    private TaskManager taskManager;
    private Storage storage;

    public DeleteCommand(ArrayList<String> splitUserInput, TaskManager taskManager, Storage storage) {
        this.splitUserInput = splitUserInput;
        this.taskManager = taskManager;
        this.storage = storage;
    }

    @Override
    public void execute() {
        if (this.splitUserInput.size() != 2) {
            Ui.showInvalidArgumentsWarning("delete");
            return;
        }

        try {
            this.taskManager.deleteTask(this.splitUserInput.get(1), this.storage);
        } catch (InvalidTaskNumberException e) {
            Ui.showInvalidTaskNumberWarning(this.taskManager);
        }
    }
}
