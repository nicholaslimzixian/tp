package seedu.address.commons.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import seedu.address.model.faculty.Faculty;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class CsvUtil {
    private static final String[] HEADERS = {"Name", "Phone Number", "Email", "Address", "Tags", "Faculties"};

    public static List<Person> readContactsFromCsv(Path csvPath)
            throws IOException, com.opencsv.exceptions.CsvValidationException {
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

    public static void writeContactsToCsv(Path csvPath, List<Person> contacts) throws IOException {
        try (Writer writer = Files.newBufferedWriter(csvPath);
             CSVWriter csvWriter = new CSVWriter(writer,
                     CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.DEFAULT_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

            csvWriter.writeNext(HEADERS);

            for (Person p : contacts) {
                String name = p.getName().toString();
                String phone = p.getPhone().toString();
                String email = p.getEmail().toString();
                String address = p.getAddress().toString();

                String tags = p.getTags().stream()
                        .map(Tag::toString)
                        .collect(Collectors.joining("|"));

                String faculties = p.getFaculties().stream()
                        .map(Faculty::toString)
                        .collect(Collectors.joining("|"));

                String[] line = {name, phone, email, address, tags, faculties};
                csvWriter.writeNext(line);
            }
        }
    }
}

