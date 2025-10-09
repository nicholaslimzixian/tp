package seedu.address.model.faculty;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Faculty in the address book.
 * Guarantees: immutable; name is valid as declared in
 * {@link #isValidFacultyName(String)}
 */
public class Faculty {

    public static final String MESSAGE_CONSTRAINTS = "Faculty names should be only contain alphabets and spaces";
    public static final String VALIDATION_REGEX = "^[a-zA-Z ]+$";

    public final String facultyName;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param facultyName A valid faculty name.
     */
    public Faculty(String facultyName) {
        requireNonNull(facultyName);
        checkArgument(isValidFacultyName(facultyName), MESSAGE_CONSTRAINTS);
        this.facultyName = facultyName;
    }

    /**
     * Returns true if a given string is a valid faculty name.
     */
    public static boolean isValidFacultyName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Faculty)) {
            return false;
        }

        Faculty otherFaculty = (Faculty) other;
        return facultyName.equals(otherFaculty.facultyName);
    }

    @Override
    public int hashCode() {
        return facultyName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + facultyName + ']';
    }

}
