package wolfie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by; // store the deadline
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | "
                + by.format(DateTimeFormatter.ISO_DATE_TIME);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
