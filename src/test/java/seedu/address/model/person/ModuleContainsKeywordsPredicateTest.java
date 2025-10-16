package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ModuleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModuleContainsKeywordsPredicate firstPredicate = new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleContainsKeywordsPredicate secondPredicate = new ModuleContainsKeywordsPredicate(
            secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleContainsKeywordsPredicate firstPredicateCopy = new ModuleContainsKeywordsPredicate(
            firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleContainsKeywords_returnsTrue() {
        // One keyword
        ModuleContainsKeywordsPredicate predicate = new ModuleContainsKeywordsPredicate(
            Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS2103T").build()));

        // Multiple keywords
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS2100"));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS2103T", "CS2101").build()));

        // Only one matching keyword
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2101"));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS2103T", "CS2101").build()));

        // Mixed-case keywords
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("cS2103t", "cs2100"));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS2103T", "CS2100").build()));
    }

    @Test
    public void test_moduleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleContainsKeywordsPredicate predicate = new ModuleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withModules("CS2103T").build()));

        // Non-matching keyword
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("CS2100"));
        assertFalse(predicate.test(new PersonBuilder().withModules("CS2103T", "CS2101").build()));

        // Keywords match name, phone, email and address, but does not match module
        predicate = new ModuleContainsKeywordsPredicate(
            Arrays.asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withModules("CS2103T").build()));
    }
}
