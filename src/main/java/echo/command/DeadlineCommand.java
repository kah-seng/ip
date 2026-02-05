package echo.command;

import echo.Storage;
import echo.Ui;
import echo.task.Deadline;
import echo.task.TaskManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private ArrayList<String> splitUserInput;
    private String userInput;
    private TaskManager taskManager;
    private Storage storage;

    public DeadlineCommand(ArrayList<String> splitUserInput, String userInput, TaskManager taskManager,
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
        int byIndex = this.userInput.indexOf(" /by ");

        if (this.splitUserInput.size() < 4 || byIndex < 0) {
            return Ui.getInvalidArgumentsWarning("deadline");
        } else {
            try {
                // Start from index 9 to remove "deadline "
                String name = this.userInput.substring(9, byIndex);
                // +5 to remove " /by "
                LocalDate by = LocalDate.parse(this.userInput.substring(byIndex + 5));
                Deadline deadline = new Deadline(name, by);
                return this.taskManager.addTask(deadline, this.storage);
            } catch (DateTimeParseException e) {
                return Ui.getInvalidArgumentsWarning("deadline");
            }
        }
    }
}
