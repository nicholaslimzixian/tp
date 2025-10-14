package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.util.FacultyDataUtil;

/**
 * Preloads the address book with faculty admin contacts.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Preloads faculty admin contacts for a given faculty.\n"
            + "Parameters: FACULTY_NAME\n"
            + "Example: " + COMMAND_WORD + " Computing";

    public static final String MESSAGE_SUCCESS =
            "Added faculty admin contacts for %1$s.";

    public static final String MESSAGE_DUPLICATE_PERSON =
            "Skipping duplicate contact: %1$s";

    public static final String MESSAGE_INVALID_FACULTY =
            "Invalid faculty specified. Please choose from the following:\n%1$s";



    private final String faculty;

    /**
     * Creates a SelectCommand to add faculty admin contacts.
     * @param faculty The faculty to add contacts for.
     */
    public SelectCommand(String faculty) {
        requireNonNull(faculty);
        this.faculty = faculty;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> facultyAdmins = FacultyDataUtil.getFacultyAdmins(faculty);

        if (facultyAdmins.isEmpty()) {
            String availableFaculties = String.join(", ", FacultyDataUtil.getAvailableFaculties());
            throw new CommandException(String.format(MESSAGE_INVALID_FACULTY, availableFaculties));
        }

        List<String> duplicateMessages = new ArrayList<>();
        for (Person person : facultyAdmins) {
            if (model.hasPerson(person)) {
                duplicateMessages.add(String.format(MESSAGE_DUPLICATE_PERSON, person.getName()));
            } else {
                model.addPerson(person);
            }
        }

        String feedbackToUser = String.format(MESSAGE_SUCCESS, faculty);
        if (!duplicateMessages.isEmpty()) {
            feedbackToUser += "\n" + String.join("\n", duplicateMessages);
        }
        return new CommandResult(feedbackToUser);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                && faculty.equalsIgnoreCase(((SelectCommand) other).faculty)); // a case-insensitive comparison
    }
}
