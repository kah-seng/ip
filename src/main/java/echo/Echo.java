package echo;

import echo.command.Command;
import echo.exception.InvalidCommandException;
import echo.task.TaskManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Echo {
    private Storage storage;

    public static void main(String[] args) {
        new Echo(Paths.get("data", "Echo.txt")).run();
    }

    public Echo(Path path) {
        this.storage = new Storage(path);
    }

    private void run() {
        Ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        try {
            taskManager = this.storage.createTaskManager();
        } catch (IOException e) {
            Ui.showFileWarning();
        }

        while (true) {
            String userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput, taskManager, storage);
                command.execute();
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidCommandException e) {
                Ui.showInvalidCommandWarning();
            }
        }
    }

    public String getResponse(String userInput) {
        return "Echo heard: " + userInput;
    }
}