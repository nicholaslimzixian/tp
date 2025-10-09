package seedu.address.commons.util;

import com.opencsv.CSVReader;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.faculty.Faculty;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvUtil {
    private static final String[] HEADERS = {"Name", "Phone Number", "Email", "Address", "Tags", "Faculties"};

    public List<Person> readContactsFromCSV(Path csvPath) throws IOException, com.opencsv.exceptions.CsvValidationException {
        List<Person> contacts = new ArrayList<>();

        try (Reader r = Files.newBufferedReader(csvPath);
             CSVReader csvReader = new CSVReader(r)) {

            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                Name name = new Name(nextLine[0].trim());
                Phone phoneNo = new Phone(nextLine[1].trim());
                Email email = new Email(nextLine[2].trim());
                Address address = new Address(nextLine[3]);
                String tag = nextLine[4];

                Set<Tag> allTags = new HashSet<>();
                if (tag != null && !tag.isEmpty()) {
                    String[] tagParts = tag.split("\\|");  // assuming '|' used to join tags
                    for (String t : tagParts) {
                        Tag newTag = new Tag(t.trim());
                        allTags.add(newTag);
                    }
                }
                String faculty = nextLine[5];

                Set<Faculty> allFaculties = new HashSet<>();
                if (faculty != null && !faculty.isEmpty()) {
                    String[] facultyParts = faculty.split("\\|");  // assuming '|' used to join tags
                    for (String t : facultyParts) {
                        Faculty newFaculty = new Faculty(t.trim());
                        allFaculties.add(newFaculty);
                    }
                }

                Person newPerson = new Person(name, phoneNo, email, address, allTags, allFaculties);

                // ADD THE NEW PERSON HERE
            }
        }
        return contacts;
    }
}
