package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.FacultyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
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

        // find by name and faculty (compound)
        Predicate<Person> combinedPredicate = new NameContainsKeywordsPredicate(List.of("Alice"))
                .and(new FacultyContainsKeywordsPredicate(List.of("Computing")));
        FindCommand expectedFindCommandCompound = new FindCommand(combinedPredicate);
        assertParseSuccess(parser, " n/Alice f/Computing", expectedFindCommandCompound);
    }

}
