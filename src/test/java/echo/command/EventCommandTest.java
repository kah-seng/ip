package echo.command;

import echo.Storage;
import echo.Ui;
import echo.task.TaskManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventCommandTest {
    @Test
    public void execute_missingFromArgument_errorMessageShown() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expected = Ui.wrapInDividers("WARNING: Invalid/Missing argument(s)\n" +
                "USAGE: event [name/description of event] /from [date] /to [date]");
        String userInput = "event meeting /to 2022-12-02";
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));

        new EventCommand(splitUserInput, userInput, null, null).execute();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void execute_missingToArgument_errorMessageShown() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expected = Ui.wrapInDividers("WARNING: Invalid/Missing argument(s)\n" +
                "USAGE: event [name/description of event] /from [date] /to [date]");
        String userInput = "event meeting /from 2022-12-02";
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));

        new EventCommand(splitUserInput, userInput, null, null).execute();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void execute_invalidDate_errorMessageShown() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expected = Ui.wrapInDividers("WARNING: Invalid/Missing argument(s)\n" +
                "USAGE: event [name/description of event] /from [date] /to [date]");
        String userInput = "event meeting /from 2222-22-22";
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));

        new EventCommand(splitUserInput, userInput, null, null).execute();
        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void execute_normalInput_success() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expected = Ui.wrapInDividers("ADDED: [E][ ] presentation (from: 12/Dec/2027, to: 13/Dec/2027)");
        String userInput = "event presentation /from 2027-12-12 /to 2027-12-13";
        ArrayList<String> splitUserInput = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        splitUserInput.removeAll(Collections.singleton(""));

        try {
            Storage storage = new Storage(Paths.get("data", "test", "Echo.txt"));
            TaskManager taskManager = storage.createTaskManager();
            new EventCommand(splitUserInput, userInput, taskManager, storage).execute();
            assertEquals(expected, outputStream.toString());
        } catch (IOException e) {
            fail();
        }
    }
}
