import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Represents settings for how the program should execute.
 */
public final class Configuration {
  public static Configuration fromFile(File file) throws IOException {
    Properties properties = new Properties();
    FileInputStream stream = new FileInputStream(file);
    properties.load(stream);
    return new Configuration(properties);
  }

  /**
   * Either a file or directory name that states should be loaded from.
   */
  public final String directory;

  /**
   * What backing implementation to use for the list of records.
   */
  public final ListType listType;

  Configuration(Hashtable<Object, Object> properties) {
    this.directory = (String) properties.get("Directory");
    this.listType = ListType.valueOf((String) properties.get("ListType"));
  }

  public enum ListType {
    ArrayList,
    LinkedList,
  }
}
