package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR_JOINED;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "/find";

    public static final String FOLLOW_MESSAGE = "Format:\n"
            + "• " + COMMAND_WORD + " " + PREFIX_ID + " ID\n"
            + "• " + COMMAND_WORD + " " + PREFIX_NAME + " NAME\n"
            + "• " + COMMAND_WORD + " " + PREFIX_PHONE + " PHONE\n"
            + "• " + COMMAND_WORD + " " + PREFIX_EMAIL + " EMAIL\n"
            + "• " + COMMAND_WORD + " " + PREFIX_YEAR_JOINED + " YEAR_JOINED\n"
            + "• " + COMMAND_WORD + " " + PREFIX_TAG + " TAG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons who match the given search criteria\n"
            + "Format:\n"
            + "• " + COMMAND_WORD + " " + PREFIX_ID + " ID\n"
            + "• " + COMMAND_WORD + " " + PREFIX_NAME + " NAME\n"
            + "• " + COMMAND_WORD + " " + PREFIX_PHONE + " PHONE\n"
            + "• " + COMMAND_WORD + " " + PREFIX_EMAIL + " EMAIL\n"
            + "• " + COMMAND_WORD + " " + PREFIX_YEAR_JOINED + " YEAR_JOINED\n"
            + "• " + COMMAND_WORD + " " + PREFIX_TAG + " TAG\n"
            + "Example: " + COMMAND_WORD + " :name Patrick";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

    public Predicate<Person> getPredicate() {
        return predicate;
    }
}
