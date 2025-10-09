package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.faculty.Faculty;
import seedu.address.model.module.Module;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(
                new Name("Alex Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"),
                getModuleSet("CS2103T", "MA1101R"),
                getFacultySet("Science")),
            new Person(
                new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("cca mate", "friends"),
                getModuleSet("CS2101"),
                getFacultySet("Business")),
            new Person(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("friends"),
                getModuleSet("GEQ1000"),
                getFacultySet("Arts")),
            new Person(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("lab partner"),
                getModuleSet("CS2105", "CS2101"),
                getFacultySet("Computing")),
            new Person(
                new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"),
                getModuleSet("CS2103T"),
                getFacultySet("Engineering")),
            new Person(
                new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("classmates"),
                getModuleSet("MA1101R", "GEQ1000"),
                getFacultySet("Business"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
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
     * Returns a module set containing the list of strings given.
     */
    public static Set<Module> getModuleSet(String... strings) {
        return Arrays.stream(strings)
                .map(Module::new)
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
