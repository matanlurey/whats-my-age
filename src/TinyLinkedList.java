import java.util.Iterator;

public final class TinyLinkedList<T> implements TinyList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size = 0;

  @Override
  public void add(T item) {
    if (head == null) {
      head = tail = new Node<>(item);
    } else {
      tail = tail.next = new Node<>(item);
    }
    size++;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(index);
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.value;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterator<T> iterator() {
    return new TinyLinkedListIterator<>(head);
  }

  private static class Node<T> {
    /**
     * Value in the node.
     */
    private final T value;

    /**
     * Next node, if any.
     */
    private Node<T> next;

    Node(T value) {
      this(value, null);
    }

    Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }

  private static class TinyLinkedListIterator<T> implements Iterator<T> {
    private Node<T> current;

    TinyLinkedListIterator(Node<T> head) {
      this.current = head;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      T value = current.value;
      current = current.next;
      return value;
    }
  }
}
