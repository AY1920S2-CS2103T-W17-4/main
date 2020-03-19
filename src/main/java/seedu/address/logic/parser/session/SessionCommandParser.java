package seedu.address.logic.parser.session;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandWords;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input into commands that interact with Session model.
 */
public class SessionCommandParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final String UNIMPLEMENTED_CODE_FORMAT = "%s not yet implemented!";

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case CommandWords.ADD_MODEL:
            return new AddSessionCommandParser().parse(arguments);

        case CommandWords.DELETE_MODEL:
            return new DeleteSessionCommandParser().parse(arguments);

        case CommandWords.EDIT_MODEL:
            return new EditSessionCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
