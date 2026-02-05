package echo;

import echo.command.Command;
import echo.exception.InvalidCommandException;
import echo.task.TaskManager;
import javafx.application.Platform;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Echo {
    private Storage storage;
    private TaskManager taskManager;

    public Echo(Path path) {
        this.storage = new Storage(path);
        try {
            this.taskManager = this.storage.createTaskManager();
        } catch (IOException e) {

        }
    }

    public void parseAndExecute(String userInput) {
        try {
            Command command = Parser.parse(userInput, this.taskManager, this.storage);
            command.execute();
            if (command.isExit()) {
                Platform.exit();
            }
        } catch (InvalidCommandException e) {
            Ui.showInvalidCommandWarning();
        }
    }
}