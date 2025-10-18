package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An immutable command history that is serializable to JSON format.
 */
@JsonRootName(value = "commandhistory")
class JsonSerializableCommandHistory {

    private final List<String> commands;

    /**
     * Constructs a {@code JsonSerializableCommandHistory} with the given commands.
     */
    @JsonCreator
    public JsonSerializableCommandHistory(@JsonProperty("commands") List<String> commands) {
        this.commands = commands != null ? new ArrayList<>(commands) : new ArrayList<>();
    }

    /**
     * Converts this command history into a list of command strings.
     *
     * @return A list of command strings.
     */
    public List<String> toModelType() {
        return new ArrayList<>(commands);
    }
}

