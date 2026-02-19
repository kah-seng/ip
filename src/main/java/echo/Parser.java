package echo;

import echo.command.*;
import echo.exception.InvalidCommandException;
import echo.task.TaskManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Parser {
    /**
     * Returns a subclass of Command according to the first word of the user's input.
     *
     * @param userInput Line that the user typed.
     * @param taskManager TaskManager instance of current execution.
     * @param storage Storage instance of current execution.
     * @return Parsed subclass of Command.
     * @throws InvalidCommandException If command does not match any existing commands.
     */
    public static Command parse(String userInput, TaskManager taskManager, Storage storage)
            throws InvalidCommandException {
        // Used Gemini to critique the code, changed line 24 to use trim() for more readable code
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.trim().split("\\s+")));
        String commandString = splitUserInput.isEmpty() ? "" : splitUserInput.get(0);

        if (userInput.equals("bye")) {
            return new ByeCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand(taskManager);
        } else if (commandString.equals("mark")) {
            return new MarkCommand(splitUserInput, taskManager, storage);
        } else if (commandString.equals("unmark")) {
            return new UnmarkCommand(splitUserInput, taskManager, storage);
        } else if (commandString.equals("deadline")) {
            return new DeadlineCommand(splitUserInput, userInput, taskManager, storage);
        } else if (commandString.equals("event")) {
            return new EventCommand(splitUserInput, userInput, taskManager, storage);
        } else if (commandString.equals("todo")) {
            return new ToDoCommand(splitUserInput, userInput, taskManager, storage);
        } else if (commandString.equals("delete")) {
            return new DeleteCommand(splitUserInput, taskManager, storage);
        } else if (commandString.equals("find")) {
            return new FindCommand(userInput, taskManager);
        } else {
            throw new InvalidCommandException();
        }
    }
}
