package wolfie.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import wolfie.exception.WolfieException;
import wolfie.task.Event;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command {
    private final String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new WolfieException("The description, start time, and end time of an event cannot be empty.");
        }
        String description = parts[0].trim();
        LocalDateTime from = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime to = LocalDateTime.parse(parts[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Task task = new Event(description, from, to, false);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }
}
