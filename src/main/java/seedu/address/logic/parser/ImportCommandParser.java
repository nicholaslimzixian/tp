package seedu.address.logic.parser;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    @Override
    public ImportCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        try {
            Path path = Paths.get(userInput);
            if (!userInput.endsWith(".csv") || !Files.exists(path) || !Files.isRegularFile(path)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE)
                );
            }
            return new ImportCommand(path);
        } catch (InvalidPathException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE), e);
        }
    }
}
