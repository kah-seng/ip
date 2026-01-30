package echo;

import echo.command.*;
import echo.task.TaskManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Parser {
    public static Command parse(String userInput, TaskManager taskManager, Storage storage) {
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));
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
        } else {
            return new InvalidCommand();
        }
    }
}
