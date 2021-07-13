/**
 * A minimal list interface for the purpose of this program only.
 *
 * @param <T>
 */
public interface TinyList<T> extends Iterable<T> {
  /**
   * Add an element to the list.
   */
  void add(T item);

  /**
   * Retrieve an element from the list at the specified index.
   */
  T get(int index);

  /**
   * Returns the size of the list.
   */
  int size();
}
