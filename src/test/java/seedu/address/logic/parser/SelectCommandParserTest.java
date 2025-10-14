package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SelectCommand;

public class SelectCommandParserTest {

    private SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        String faculty = "Computing";
        SelectCommand expectedSelectCommand = new SelectCommand(faculty);
        assertParseSuccess(parser, faculty, expectedSelectCommand);

        // Test with extra whitespace
        assertParseSuccess(parser, "  " + faculty + "  ", expectedSelectCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Test with empty string
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }
}
