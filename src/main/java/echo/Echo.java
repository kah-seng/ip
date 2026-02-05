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

//    public static void main(String[] args) {
//        new Echo(Paths.get("data", "Echo.txt")).run();
//    }

    public Echo(Path path) {
        this.storage = new Storage(path);
        try {
            this.taskManager = this.storage.createTaskManager();
        } catch (IOException e) {

        }
    }

//    private void run() {
//        Ui.showWelcome();
//
//        Scanner scanner = new Scanner(System.in);
//        TaskManager taskManager = new TaskManager();
//        try {
//            taskManager = this.storage.createTaskManager();
//        } catch (IOException e) {
//            Ui.showFileWarning();
//        }
//
//        while (true) {
//            String userInput = scanner.nextLine();
//            try {
//                Command command = Parser.parse(userInput, taskManager, storage);
//                command.execute();
//                if (command.isExit()) {
//                    break;
//                }
//            } catch (InvalidCommandException e) {
//                Ui.showInvalidCommandWarning();
//            }
//        }
//    }

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