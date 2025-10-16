package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FacultyContainsKeywordsPredicate;
import seedu.address.model.person.ModuleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "      ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleArgs_returnsFindCommand() {
        // find by name
        FindCommand expectedFindCommandName =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommandName);
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommandName);

        // find by tag
        FindCommand expectedFindCommandTag =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friend", "cca")));
        assertParseSuccess(parser, " " + PREFIX_TAG + "friend cca", expectedFindCommandTag);
        assertParseSuccess(parser, " " + PREFIX_TAG + " \n friend \n \t cca  \t", expectedFindCommandTag);

        // find by faculty
        FindCommand expectedFindCommandFaculty =
                new FindCommand(new FacultyContainsKeywordsPredicate(Arrays.asList("Computing", "Science")));
        assertParseSuccess(parser, " " + PREFIX_FACULTY + "Computing Science", expectedFindCommandFaculty);
        assertParseSuccess(parser, " " + PREFIX_FACULTY + " \n Computing \n \t Science  \t", expectedFindCommandFaculty);

        // find by module
        List<String> moduleKeywords = Arrays.asList("CS2103T", "CS2100");
        FindCommand expectedFindCommandModule =
                new FindCommand(new ModuleContainsKeywordsPredicate(moduleKeywords));
        assertParseSuccess(parser, " " + PREFIX_MODULE + "CS2103T CS2100", expectedFindCommandModule);

        // find by module with multiple whitespaces
        assertParseSuccess(parser, " " + PREFIX_MODULE + " \n CS2103T \n \t CS2100  \t", expectedFindCommandModule);
    }

    @Test
    public void parse_validCompoundArgs_returnsFindCommand() throws ParseException {
        // Parse a command with multiple different prefixes
        FindCommand command = parser.parse(" n/Alice f/Computing m/CS2103T");
        Predicate<Person> predicate = command.getPredicate();

        // A person that matches all criteria
        Person aliceInComputingWithModule = new PersonBuilder()
                .withName("Alice Tan")
                .withFaculties("Computing")
                .withModules("CS2103T")
                .build();

        // A person that matches name and faculty, but not module
        Person aliceInComputingWithoutModule = new PersonBuilder()
                .withName("Alice Lim")
                .withFaculties("Computing")
                .withModules("CS2100")
                .build();

        // A person that matches name, but not faculty and module
        Person aliceInScienceWithModule = new PersonBuilder()
                .withName("Alice Wong")
                .withFaculties("Science")
                .withModules("CS2103T")
                .build();

        // A person that matches faculty and module, but not name
        Person bobInComputingWithModule = new PersonBuilder()
                .withName("Bob Lee")
                .withFaculties("Computing")
                .withModules("CS2103T")
                .build();

        // Assert that the predicate filters correctly
        assertTrue(predicate.test(aliceInComputingWithModule));
        assertFalse(predicate.test(aliceInComputingWithoutModule));
        assertFalse(predicate.test(aliceInScienceWithModule));
        assertFalse(predicate.test(bobInComputingWithModule));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        // no prefix
        assertParseFailure(parser, "Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // invalid value after prefix - empty name
        assertParseFailure(parser, " " + PREFIX_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // invalid value after prefix - empty tag
        assertParseFailure(parser, " " + PREFIX_TAG,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // invalid value after prefix - empty faculty
        assertParseFailure(parser, " " + PREFIX_FACULTY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // invalid value after prefix - empty module
        assertParseFailure(parser, " " + PREFIX_MODULE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
