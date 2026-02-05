package echo.command;

import echo.Storage;
import echo.Ui;
import echo.task.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DeadlineCommandTest {
    @Test
    public void execute_missingByArgument_errorMessageShown() {
        String expected = Ui.getInvalidArgumentsWarning("deadline");
        String userInput = "deadline homework";
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));

        String actual = new DeadlineCommand(splitUserInput, userInput, null, null).execute();
        assertEquals(expected, actual);
    }

    @Test
    public void execute_invalidDate_errorMessageShown() {
        String expected = Ui.getInvalidArgumentsWarning("deadline");
        String userInput = "deadline assignment /by 19 March 2027";
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));

        String actual = new DeadlineCommand(splitUserInput, userInput, null, null).execute();
        assertEquals(expected, actual);
    }

    @Test
    public void execute_normalInput_success() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expected = "ADDED: [D][ ] homework (by: 10/Mar/2027)";
        String userInput = "deadline homework /by 2027-03-10";
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));

        try {
            Storage storage = new Storage(Paths.get("data", "test", "Echo.txt"));
            TaskManager taskManager = storage.createTaskManager();
            String actual = new DeadlineCommand(splitUserInput, userInput, taskManager, storage).execute();
            assertEquals(expected, actual);
        } catch (IOException e) {
            fail();
        }
    }
}
