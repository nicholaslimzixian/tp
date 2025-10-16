package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Module} matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ModuleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        }
        return person.getModules().stream()
                .anyMatch(module -> keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.moduleName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleContainsKeywordsPredicate)) {
            return false;
        }

        ModuleContainsKeywordsPredicate otherPredicate = (ModuleContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }
}
