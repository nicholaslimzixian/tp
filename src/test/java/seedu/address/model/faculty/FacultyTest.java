package seedu.address.model.faculty;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.seedu.address.model.faculty.Faculty;

public class FacultyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Faculty(null));
    }

    @Test
    public void constructor_invalidFacultyName_throwsIllegalArgumentException() {
        String invalidFacultyName = "";
        assertThrows(IllegalArgumentException.class, () -> new Faculty(invalidFacultyName));
    }

    @Test
    public void isValidFacultyName() {
        // null faculty name
        assertThrows(NullPointerException.class, () -> Faculty.isValidFacultyName(null));
    }

}
