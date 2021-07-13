import java.util.Arrays;
import java.util.Iterator;

public class TinyArrayList<T> implements TinyList<T> {
  @SuppressWarnings("unchecked")
  private T[] array = (T[]) new Object[10];
  private int size = 0;

  @Override
  public void add(T item) {
    if (size >= array.length) {
      array = Arrays.copyOf(array, size * 2);
    }
    array[size] = item;
    size++;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(index);
    }
    return array[index];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<T> iterator() {
    return new TinyArrayListIterator<>(this);
  }

  private static class TinyArrayListIterator<T> implements Iterator<T> {
    private final TinyArrayList<T> list;
    private int current = 0;

    TinyArrayListIterator(TinyArrayList<T> list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return current < list.size;
    }

    @Override
    public T next() {
      T value = list.get(current);
      current++;
      return value;
    }
  }
}
