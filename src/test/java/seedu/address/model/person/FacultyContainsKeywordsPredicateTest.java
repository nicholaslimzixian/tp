package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FacultyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FacultyContainsKeywordsPredicate firstPredicate =
                new FacultyContainsKeywordsPredicate(firstPredicateKeywordList);
        FacultyContainsKeywordsPredicate secondPredicate =
                new FacultyContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FacultyContainsKeywordsPredicate firstPredicateCopy =
                new FacultyContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_facultyContainsKeywords_returnsTrue() {
        // One keyword
        FacultyContainsKeywordsPredicate predicate =
                new FacultyContainsKeywordsPredicate(Collections.singletonList("Science"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withFaculties("Science").build()));

        // Multiple keywords
        predicate = new FacultyContainsKeywordsPredicate(Arrays.asList("Science", "Business"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withFaculties("Science").build()));

        // Only one matching keyword
        predicate = new FacultyContainsKeywordsPredicate(Arrays.asList("Business", "Engineering"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice")
                .withFaculties("Engineering", "Science").build()));

        // Mixed-case keywords
        predicate = new FacultyContainsKeywordsPredicate(Arrays.asList("sCIeNce", "bUSIness"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice")
                .withFaculties("Science", "Business").build()));
    }

    @Test
    public void test_facultyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FacultyContainsKeywordsPredicate predicate = new FacultyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withFaculties("Science").build()));

        // Non-matching keyword
        predicate = new FacultyContainsKeywordsPredicate(Arrays.asList("Law"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withFaculties("Science", "Business").build()));

        // Keywords match name, phone, email and address, but does not match faculty
        predicate = new FacultyContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withFaculties("Science").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        FacultyContainsKeywordsPredicate predicate = new FacultyContainsKeywordsPredicate(keywords);

        String expected = FacultyContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
