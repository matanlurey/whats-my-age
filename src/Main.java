import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public final class Main {
  public static void main(String[] args) {
    File file = new File("./SampleConfig.txt");
    Configuration configuration;
    try {
      configuration = Configuration.fromFile(file);
    } catch (IOException e) {
      System.err.println("Could not load file: \"" + file.getPath() + "\".");
      return;
    }

    File directory = new File(configuration.directory);
    HashMap<String, TinyList<Record>> states = loadStates(directory, configuration.listType);
    Predictor predictor = new Predictor(states);
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Name of the person (or EXIT to quit):");
      String name = scanner.next();
      if (name.equals("EXIT")) {
        return;
      }
      System.out.println("Gender (M/F):");
      String genderCode = scanner.next();
      System.out.println("State of birth (two-letter state code):");
      String state = scanner.next();

      Record.Gender gender;
      if (genderCode.equals("M")) {
        gender = Record.Gender.Male;
      } else if (genderCode.equals("F")) {
        gender = Record.Gender.Female;
      } else {
        System.err.println("Unexpected gender: \"" + genderCode + "\".");
        continue;
      }

      int frequency = predictor.predict(name, gender, state);
      if (frequency == -1) {
        System.err.println("Insufficient data.");
      } else {
        System.out.println(name + ", born in " + state + " (" + genderCode + ") is most likely around " + frequency + " years old.");
      }
    }
  }

  private static HashMap<String, TinyList<Record>> loadStates(File directory, Configuration.ListType listType) {
    HashMap<String, TinyList<Record>> states = new HashMap<>();
    if (directory.isDirectory()) {
      for (File f : Objects.requireNonNull(directory.listFiles())) {
        TinyList<Record> state = readFile(f.getPath(), listType);
        if (state.size() > 0) {
          String stateCode = state.get(0).state;
          states.put(stateCode, state);
        }
      }
    } else if (directory.isFile()) {
      TinyList<Record> state = readFile(directory.getPath(), listType);
      if (state.size() > 0) {
        String stateCode = state.get(0).state;
        states.put(stateCode, state);
      }
    } else {
      System.err.println("Invalid file or directory: \"" + directory.getPath() + "\".");
    }
    return states;
  }

  private static TinyList<Record> readFile(String fileName, Configuration.ListType listType) {
    TinyList<Record> records;
    switch (listType) {
      case ArrayList -> records = new TinyArrayList<>();
      case LinkedList -> records = new TinyLinkedList<>();
      default -> throw new IllegalStateException("Unexpected value: " + listType);
    }
    RecordReader reader = new RecordReader();
    try {
      reader.readFile(fileName, records);
    } catch (IOException e) {
      System.err.println("Invalid file: \"" + fileName + "\".");
    }
    return records;
  }

  private Main() {
  }
}
