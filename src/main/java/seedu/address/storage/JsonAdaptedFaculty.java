package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.faculty.Faculty;

/**
 * Jackson-friendly version of {@link Faculty}.
 */
class JsonAdaptedFaculty {

    private final String facultyName;

    /**
     * Constructs a {@code JsonAdaptedFaculty} with the given {@code facultyName}.
     */
    @JsonCreator
    public JsonAdaptedFaculty(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * Converts a given {@code Faculty} into this class for Jackson use.
     */
    public JsonAdaptedFaculty(Faculty source) {
        facultyName = source.facultyName;
    }

    @JsonValue
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * Converts this Jackson-friendly adapted faculty object into the model's
     * {@code Faculty} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted faculty.
     */
    public Faculty toModelType() throws IllegalValueException {
        if (!Faculty.isValidFacultyName(facultyName)) {
            throw new IllegalValueException(Faculty.MESSAGE_CONSTRAINTS);
        }
        return new Faculty(facultyName);
    }

}
