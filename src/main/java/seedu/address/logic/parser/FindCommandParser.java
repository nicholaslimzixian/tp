package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FacultyContainsKeywordsPredicate;
import seedu.address.model.person.ModuleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_FACULTY, PREFIX_MODULE);

        if (!isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_TAG, PREFIX_FACULTY, PREFIX_MODULE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Throws ParseException if prefixes are repeated e.g. n/Alice n/Bob
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_TAG, PREFIX_FACULTY, PREFIX_MODULE);

        List<Predicate<Person>> predicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String nameKeywords = argMultimap.getValue(PREFIX_NAME).get();
            if (nameKeywords.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicates.add(new NameContainsKeywordsPredicate(List.of(nameKeywords.split("\\s+"))));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String tagKeywords = argMultimap.getValue(PREFIX_TAG).get();
            if (tagKeywords.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicates.add(new TagContainsKeywordsPredicate(List.of(tagKeywords.split("\\s+"))));
        }
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            String facultyKeywords = argMultimap.getValue(PREFIX_FACULTY).get();
            if (facultyKeywords.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicates.add(new FacultyContainsKeywordsPredicate(List.of(facultyKeywords.split("\\s+"))));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            String moduleKeywords = argMultimap.getValue(PREFIX_MODULE).get();
            if (moduleKeywords.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            predicates.add(new ModuleContainsKeywordsPredicate(List.of(moduleKeywords.split("\\s+"))));
        }

        // Combine all predicates with an 'AND' logic
        Predicate<Person> combinedPredicate = predicates.stream().reduce(Predicate::and).orElse(x -> true);

        return new FindCommand(combinedPredicate);
    }

    /**
     * Returns true if at least one of the prefixes is present in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
