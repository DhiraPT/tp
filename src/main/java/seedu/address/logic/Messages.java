package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_UNKNOWN_CONFIRMATION_COMMAND = "Please input Y/N to confirm or abort deletion of"
            + " the following user:\n"
            + "%1$s";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String TAG_INVALID_INDEX = "The tag index provided is invalid";
    public static final String TAG_NO_TAG_PRESENT = "No tag is present and please use /tag function to add tags!";
    public static final String TAG_DUPLICATE_TAG = "The tag already exist";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_ID = "The person with the ID provided is not found";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_DUPLICATE_TAGS =
            "Cannot add duplicated tags to one employee.";
    public static final String EDIT_SAME_FIELD =
            "Some edited field is the same as the original field.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; ID: ")
                .append(person.getId())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code transaction} for display to the user.
     */
    public static String format(Transaction transaction) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Transaction ID: ")
                .append(transaction.getId())
                .append("; DateTime: ")
                .append(transaction.getDateTime())
                .append("; Employee ID: ")
                .append(transaction.getEmployeeId())
                .append("; Amount: ")
                .append(transaction.getAmount())
                .append("; Description: ")
                .append(transaction.getDescription());
        return builder.toString();
    }

}
