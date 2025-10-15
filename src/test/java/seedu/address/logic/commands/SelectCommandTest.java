package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.util.FacultyDataUtil;


/**
 * Contains integration tests (interaction with the Model) and unit tests for SelectCommand.
 */
public class SelectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validFaculty_success() {
        String faculty = "Computing";
        SelectCommand selectCommand = new SelectCommand(faculty);

        String expectedMessage = String.format(SelectCommand.MESSAGE_SUCCESS, faculty);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Person> facultyAdmins = FacultyDataUtil.getFacultyAdmins(faculty);
        for (Person admin : facultyAdmins) {
            expectedModel.addPerson(admin);
        }

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidFaculty_throwsCommandException() {
        String invalidFaculty = "InvalidFaculty";
        SelectCommand selectCommand = new SelectCommand(invalidFaculty);
        String availableFaculties = String.join(", ", FacultyDataUtil.getAvailableFaculties());
        String expectedMessage = String.format(SelectCommand.MESSAGE_INVALID_FACULTY, availableFaculties);

        assertCommandFailure(selectCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicatePerson_skipsAndShowsWarning() {
        String faculty = "Computing";
        // Pre-add one of the contacts to the model
        Person existingPerson = FacultyDataUtil.getFacultyAdmins(faculty).get(0);
        model.addPerson(existingPerson);

        SelectCommand selectCommand = new SelectCommand(faculty);

        // Build expected message
        String successMessage = String.format(SelectCommand.MESSAGE_SUCCESS, faculty);
        String duplicateMessage = String.format(SelectCommand.MESSAGE_DUPLICATE_PERSON, existingPerson.getName());
        String expectedMessage = successMessage + "\n" + duplicateMessage;

        // Build expected model state
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        List<Person> facultyAdmins = FacultyDataUtil.getFacultyAdmins(faculty);
        for (Person admin : facultyAdmins) {
            expectedModel.addPerson(admin);
        }

        assertCommandSuccess(selectCommand, model, expectedMessage, expectedModel);
    }
}
