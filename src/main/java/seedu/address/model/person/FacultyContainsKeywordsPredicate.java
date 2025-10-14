package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.faculty.Faculty;

/**
 * Tests that a {@code Person}'s {@code Faculty} matches any of the keywords given.
 */
public class FacultyContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FacultyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        }
        return person.getFaculties().stream()
                .anyMatch(faculty -> keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(faculty.facultyName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FacultyContainsKeywordsPredicate)) {
            return false;
        }

        FacultyContainsKeywordsPredicate otherPredicate = (FacultyContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
