package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

/**
 * Imports a CSV file and loads the contacts into the address book.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_IMPORT_SUCCESS = "Import successful";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports all the contacts from a CSV file and adds it to the current address book. "
            + "The address book will ignore any duplicates and"
            + " incorrect data formats.\n"
            + "Parameters: Path file (must be the full path)\n"
            + "Example: " + COMMAND_WORD + " C://Users//djsud//Downloads//CampusBook_contacts.csv/";

    public Path path;

    public ImportCommand(String path) {
        requireNonNull(path);
        this.path = Paths.get(path);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
