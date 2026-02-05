package echo.command;

import echo.Storage;
import echo.Ui;
import echo.exception.InvalidTaskNumberException;
import echo.task.TaskManager;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private ArrayList<String> splitUserInput;
    private TaskManager taskManager;
    private Storage storage;

    public UnmarkCommand(ArrayList<String> splitUserInput, TaskManager taskManager, Storage storage) {
        this.splitUserInput = splitUserInput;
        this.taskManager = taskManager;
        this.storage = storage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        if (this.splitUserInput.size() != 2) {
            return Ui.getInvalidArgumentsWarning("unmark");
        }

        try {
            return this.taskManager.unmarkTask(this.splitUserInput.get(1), this.storage);
        } catch (InvalidTaskNumberException e) {
            return Ui.getInvalidTaskNumberWarning(this.taskManager);
        }
    }
}
