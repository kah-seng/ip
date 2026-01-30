package echo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate from;
    LocalDate to;

    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")));
    }
}
