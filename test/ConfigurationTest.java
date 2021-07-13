import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {
  private Configuration configuration;

  @Test
  void test_ArrayList() {
    Hashtable<Object, Object> props = new Hashtable<>();
    props.put("Directory", "./SomeDir");
    props.put("ListType", "ArrayList");
    configuration = new Configuration(props);

    assertEquals(configuration.directory, "./SomeDir");
    assertEquals(configuration.listType, Configuration.ListType.ArrayList);
  }

  @Test
  void test_LinkedList() {
    Hashtable<Object, Object> props = new Hashtable<>();
    props.put("Directory", "./SomeDir");
    props.put("ListType", "LinkedList");
    configuration = new Configuration(props);

    assertEquals(configuration.directory, "./SomeDir");
    assertEquals(configuration.listType, Configuration.ListType.LinkedList);
  }
}
