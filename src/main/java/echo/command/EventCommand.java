package echo.command;

import echo.Storage;
import echo.Ui;
import echo.task.Event;
import echo.task.TaskManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EventCommand extends Command {
    private ArrayList<String> splitUserInput;
    private String userInput;
    private TaskManager taskManager;
    private Storage storage;

    public EventCommand(ArrayList<String> splitUserInput, String userInput, TaskManager taskManager, Storage storage) {
        this.splitUserInput = splitUserInput;
        this.userInput = userInput;
        this.taskManager = taskManager;
        this.storage = storage;
    }

    @Override
    public void execute() {
        int fromIndex = this.userInput.indexOf(" /from ");
        int toIndex = this.userInput.indexOf(" /to ");

        if (this.splitUserInput.size() < 6 || fromIndex < 0 || toIndex < 0 || toIndex < fromIndex) {
            Ui.showInvalidArgumentsError("event");
        } else {
            try {
                // Start from index 6 to remove "event "
                String name = this.userInput.substring(6, fromIndex);
                // +7 to remove " /from "
                LocalDate from = LocalDate.parse(this.userInput.substring(fromIndex + 7, toIndex));
                // +5 to remove " /to "
                LocalDate to = LocalDate.parse(this.userInput.substring(toIndex + 5));
                Event event = new Event(name, from, to);
                this.taskManager.addTask(event, this.storage);
            } catch (DateTimeParseException e) {
                Ui.showInvalidArgumentsError("event");
            }
        }
    }
}
