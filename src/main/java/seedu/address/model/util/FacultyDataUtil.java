package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.faculty.Faculty;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating the address book with faculty admin contacts.
 */
public class FacultyDataUtil {

    /**
     * Returns a list of all available faculty names.
     * @return A list of faculty names.
     */
    public static List<String> getAvailableFaculties() {
        return List.of("Science", "Business", "Arts", "Computing", "Engineering", "Law", "Medicine");
    }

    /**
     * Returns a list of admin contacts for a given faculty.
     * @param facultyName The name of the faculty.
     * @return A list of admin contacts.
     */
    public static List<Person> getFacultyAdmins(String facultyName) {
        List<Person> adminContacts = new ArrayList<>();
        switch (facultyName.toLowerCase()) {
        case "science":
            adminContacts.add(new Person(new Name("Science Admin"), new Phone("81234567"),
                    new Email("sci_admin@example.com"), new Address("Science Block S1"),
                    getTagSet("admin"), getFacultySet("Science")));
            break;
        case "business":
            adminContacts.add(new Person(new Name("Business Admin"), new Phone("82345678"),
                    new Email("biz_admin@example.com"), new Address("Business School Mochtar Riady Building"),
                    getTagSet("admin"), getFacultySet("Business")));
            break;
        case "arts":
            adminContacts.add(new Person(new Name("Arts Admin"), new Phone("83456789"),
                    new Email("fass_admin@example.com"), new Address("Faculty of Arts and Social Sciences"),
                    getTagSet("admin"), getFacultySet("Arts")));
            break;
        case "computing":
            adminContacts.add(new Person(new Name("Computing Admin"), new Phone("84567890"),
                    new Email("soc_admin@example.com"), new Address("School of Computing COM1"),
                    getTagSet("admin"), getFacultySet("Computing")));
            break;
        case "engineering":
            adminContacts.add(new Person(new Name("Engineering Admin"), new Phone("85678901"),
                    new Email("eng_admin@example.com"), new Address("Faculty of Engineering"),
                    getTagSet("admin"), getFacultySet("Engineering")));
            break;
        case "law":
            adminContacts.add(new Person(new Name("Law Admin"), new Phone("86789012"),
                    new Email("law_admin@example.com"), new Address("Faculty of Law"),
                    getTagSet("admin"), getFacultySet("Law")));
            break;
        case "medicine":
            adminContacts.add(new Person(new Name("Medicine Admin"), new Phone("87890123"),
                    new Email("med_admin@example.com"), new Address("Yong Loo Lin School of Medicine"),
                    getTagSet("admin"), getFacultySet("Medicine")));
            break;
        default:
            // Return an empty list if faculty not found
            break;
        }
        return adminContacts;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a faculty set containing the list of strings given.
     */
    public static Set<Faculty> getFacultySet(String... strings) {
        return Arrays.stream(strings)
                .map(Faculty::new)
                .collect(Collectors.toSet());
    }
}
