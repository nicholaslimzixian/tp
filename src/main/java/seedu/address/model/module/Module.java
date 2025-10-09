package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in the address book.
 * Guarantees: immutable; name is valid as declared in
 * {@link #isValidModuleName(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Module names should consist of 2 to 4 uppercase letters, followed by 4 digits, "
            + "and optionally ending with 1/2 uppercase letter. For example, CS2103T, GESS1101, CS2040DE.";
    public static final String VALIDATION_REGEX = "^[A-Z]{2,4}\\d{4}[A-Z]?[A-Z]?$";

    public final String moduleName;

    /**
     * Constructs a {@code Module}.
     *
     * @param moduleName A valid module name.
     */
    public Module(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return moduleName.equals(otherModule.moduleName);
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + moduleName + ']';
    }

}
