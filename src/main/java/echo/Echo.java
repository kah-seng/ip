package echo;

import echo.command.Command;
import echo.exception.InvalidCommandException;
import echo.task.TaskManager;
import javafx.application.Platform;

import java.io.IOException;
import java.nio.file.Path;

public class Echo {
    private Storage storage;
    private TaskManager taskManager;

    public Echo(Path path) {
        try {
            this.storage = new Storage(path);
            this.taskManager = this.storage.createTaskManager();
        } catch (IOException e) {
            Platform.exit();
        }
    }

    public String parseAndExecute(String userInput) {
        try {
            Command command = Parser.parse(userInput, this.taskManager, this.storage);
            if (command.isExit()) {
                Platform.exit();
            }
            return command.execute();
        } catch (InvalidCommandException e) {
            return Ui.getInvalidCommandWarning();
        }
    }
}