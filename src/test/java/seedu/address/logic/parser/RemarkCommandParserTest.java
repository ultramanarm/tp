package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

public class RemarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    private RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_returnsRemarkCommand() {
        assertParseSuccess(parser, "1 r/Likes coffee",
                new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Likes coffee")));
    }

    @Test
    public void parse_surroundingWhitespace_returnsRemarkCommand() {
        assertParseSuccess(parser, "  1  r/  Likes coffee  ",
                new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Likes coffee")));
    }

    @Test
    public void parse_emptyRemark_returnsCommandToClearRemark() {
        assertParseSuccess(parser, "1 r/", new RemarkCommand(INDEX_FIRST_PERSON, new Remark("")));
        assertParseSuccess(parser, "1 r/   ", new RemarkCommand(INDEX_FIRST_PERSON, new Remark("")));
    }

    @Test
    public void parse_missingPrefix_returnsCommandToClearRemark() {
        assertParseSuccess(parser, "1", new RemarkCommand(INDEX_FIRST_PERSON, new Remark("")));
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " r/Likes coffee", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "0 r/Likes coffee", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1 r/Likes coffee", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "abc r/Likes coffee", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1.5 r/Likes coffee", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "2147483648 r/Likes coffee", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_unexpectedPreamble_failure() {
        assertParseFailure(parser, "1 Likes coffee", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 n/Alice r/Likes coffee", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_duplicateRemarkPrefix_failure() {
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(PREFIX_REMARK);
        assertParseFailure(parser, "1 r/Likes coffee r/Prefers tea", expectedMessage);
        assertParseFailure(parser, "1 r/Likes coffee r/", expectedMessage);
        assertParseFailure(parser, "1 r/ r/Likes coffee", expectedMessage);
    }
}
