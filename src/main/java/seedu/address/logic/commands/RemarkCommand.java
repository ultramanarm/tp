package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Handles the remark command.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Remark command is connected.");
    }
}