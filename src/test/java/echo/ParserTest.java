package echo;

import echo.command.*;
import echo.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_invalidCommand_exceptionThrown() {
        try {
            Command command = Parser.parse("random test", null, null);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("Invalid command", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_byeCommand_success() {
        try {
            Command command = Parser.parse("bye", null, null);
            assertInstanceOf(ByeCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void parse_deadlineCommand_success() {
        try {
            Command command = Parser.parse("deadline homework /by 2027-12-12", null, null);
            assertInstanceOf(DeadlineCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_success() {
        try {
            Command command = Parser.parse("delete 1", null, null);
            assertInstanceOf(DeleteCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void parse_eventCommand_success() {
        try {
            Command command = Parser.parse("event meeting /from 2027-12-12 /to 2027-12-13", null, null);
            assertInstanceOf(EventCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void parse_listCommand_success() {
        try {
            Command command = Parser.parse("list", null, null);
            assertInstanceOf(ListCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_success() {
        try {
            Command command = Parser.parse("mark 5", null, null);
            assertInstanceOf(MarkCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void parse_toDoCommand_success() {
        try {
            Command command = Parser.parse("todo borrow book", null, null);
            assertInstanceOf(ToDoCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }

    @Test
    public void parse_unmarkCommand_success() {
        try {
            Command command = Parser.parse("unmark 10000", null, null);
            assertInstanceOf(UnmarkCommand.class, command);
        } catch (InvalidCommandException e) {
            fail();
        }
    }
}
