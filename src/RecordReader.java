import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * A basic CSV reader (no quoted elements) that returns {@link Record} instances.
 */
public final class RecordReader {
  /**
   * Adds many record instances by reading CSV entries line by line from {@param file}.
   */
  public void readFile(String fileName, TinyList<Record> records) throws IOException {
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
      stream.forEach(l -> records.add(readLine(l)));
    }
  }

  /**
   * Reads a single comma separated value (line) as a {@link Record}.
   */
  public Record readLine(String line) {
    String[] parts = line.split(",");
    if (parts.length != 5) {
      throw new IllegalArgumentException("Cannot parse: \"" + line + "\".");
    }
    String state = parts[0];
    Record.Gender gender;
    if (parts[1].equals("F")) {
      gender = Record.Gender.Female;
    } else if (parts[1].equals("M")) {
      gender = Record.Gender.Male;
    } else {
      throw new IllegalArgumentException("Unexpected gender: \"" + parts[1] + "\".");
    }
    int year = Integer.parseInt(parts[2]);
    String name = parts[3];
    int frequency = Integer.parseInt(parts[4]);
    return new Record(state, gender, year, name, frequency);
  }
}
