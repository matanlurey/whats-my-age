import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TinyArrayListTest {
  TinyList<String> list;

  @BeforeEach
  void setUp() {
    list = new TinyArrayList<>();
  }

  @Test
  void test_addFirst() {
    list.add("A");
    assertIterableEquals(List.of("A"), list);
  }

  @Test
  void test_addSecond() {
    list.add("A");
    list.add("B");
    assertIterableEquals(List.of("A", "B"), list);
  }

  @Test
  void test_getOutOfBoundsNegative() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
  }

  @Test
  void test_getOutOfBoundsPositive() {
    list.add("A");
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
  }

  @Test
  void test_sizeEmpty() {
    assertEquals(list.size(), 0);
  }

  @Test
  void test_size1() {
    list.add("A");
    assertEquals(list.size(), 1);
  }

  @Test
  void test_iterator() {
    list.add("A");
    list.add("B");
    list.add("C");
    List<String> from = new ArrayList<>();
    list.forEach(from::add);
    assertEquals(from, List.of("A", "B", "C"));
  }
}
