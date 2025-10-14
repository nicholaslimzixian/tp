package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FacultyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleArgs_returnsFindCommand() {
        // find by name
        FindCommand expectedFindCommandName =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommandName);

        // find by tag
        FindCommand expectedFindCommandTag =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friend", "cca")));
        assertParseSuccess(parser, " t/friend cca", expectedFindCommandTag);

        // find by faculty
        FindCommand expectedFindCommandFaculty =
                new FindCommand(new FacultyContainsKeywordsPredicate(Arrays.asList("Computing", "Science")));
        assertParseSuccess(parser, " f/Computing Science", expectedFindCommandFaculty);
    }

    @Test
    public void parse_validCompoundArgs_returnsFindCommand() throws ParseException {
        // Parse a command with compound predicates
        FindCommand command = parser.parse(" n/Alice f/Computing");
        Predicate<Person> predicate = command.getPredicate();

        // Create test persons
        Person aliceInComputing = new PersonBuilder().withName("Alice Tan").withFaculties("Computing").build();
        Person bobInComputing = new PersonBuilder().withName("Bob Lee").withFaculties("Computing").build();
        Person aliceInScience = new PersonBuilder().withName("Alice Lim").withFaculties("Science").build();

        // Assert that the predicate filters correctly
        assertTrue(predicate.test(aliceInComputing));
        assertFalse(predicate.test(bobInComputing));
        assertFalse(predicate.test(aliceInScience));
    }
}
