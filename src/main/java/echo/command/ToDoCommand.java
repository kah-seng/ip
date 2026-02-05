package echo.command;

import echo.Storage;
import echo.Ui;
import echo.task.TaskManager;
import echo.task.ToDo;

import java.util.ArrayList;

public class ToDoCommand extends Command {
    private ArrayList<String> splitUserInput;
    private String userInput;
    private TaskManager taskManager;
    private Storage storage;

    public ToDoCommand(ArrayList<String> splitUserInput, String userInput, TaskManager taskManager,
                       Storage storage) {
        this.splitUserInput = splitUserInput;
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.storage = storage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        if (this.splitUserInput.size() < 2) {
            return Ui.getInvalidArgumentsWarning("todo");
        } else {
            ToDo toDo = new ToDo(this.userInput.substring(5));
            return this.taskManager.addTask(toDo, this.storage);
        }
    }
}
