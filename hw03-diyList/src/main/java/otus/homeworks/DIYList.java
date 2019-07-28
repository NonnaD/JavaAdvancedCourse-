package otus.homeworks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class DIYList<T>
    implements List<T> {

  private Object[] dataArray = {};
  private Object[] emptyArray = {};
  private Object[] printArray = new Object[100];
  private int arraySize;

  public DIYList() {
    this.dataArray = emptyArray;
  }

  public DIYList(Collection<? extends T> e) {
    dataArray = e.toArray();
    if ((arraySize = dataArray.length) != 0) {
      if (dataArray.getClass() != Object[].class) {
        dataArray = Arrays.copyOf(dataArray, arraySize, Object[].class);
      }
    } else {
      this.dataArray = emptyArray;
    }
  }

  @Override
  public String toString() {
    Iterator<T> it = iterator();
    if (! it.hasNext())
      return "[]";

    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (;;) {
      T e = it.next();
      sb.append(e == this ? "(this Collection)" : e);
      if (! it.hasNext())
        return sb.append(']').toString();
      sb.append(',').append(' ');
    }
  }

  @Override
  public int size() {
    return arraySize;
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public Iterator<T> iterator() {
    return listIterator();
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(dataArray, arraySize);
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public boolean add(T t) {
    add(arraySize, dataArray, t);
    return true;
  }

  private void add(int s, Object[] array, T element) {
    if (s == array.length || s < 0) {
      array = extendArray(arraySize + 8);
    }
    array[s] = element;
    arraySize++;
  }


  private Object[] extendArray(int newSize) {
    return dataArray = Arrays.copyOf(dataArray,
        newSize);
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public T get(int index) {
    Objects.checkIndex(index, arraySize);
    return (T) dataArray[index];
  }

  @Override
  public T set(int index, T element) {
    Objects.checkIndex(index, arraySize);
    T oldValue = elementData(index);
    dataArray[index] = element;
    return oldValue;
  }

  T elementData(int index) {
    return (T) dataArray[index];
  }

  @Override
  public void add(int index, T element) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public T remove(int index) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    rangeCheckForAdd(index);
    return new DIYList.ListItr(index);
  }


  private void rangeCheckForAdd(int index) {
    if (index > arraySize || index < 0)
      throw new IndexOutOfBoundsException("Index: "+index+", Size: "+ arraySize);
  }

  @Override
  public ListIterator<T> listIterator() {
    return new DIYList.ListItr(0);
  }


  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException("Method is not implemented");
  }

  static <E> E elementAt(Object[] es, int index) {
    return (E) es[index];
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sort(Comparator<? super T> c) {
    Arrays.sort((T[]) dataArray, 0, arraySize, c);
  }


  /**
   * An optimized version of AbstractList.Itr
   */
  private class Itr implements Iterator<T> , ListIterator<T> {
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    int expectedModCount = 0;

    // prevent creating a synthetic constructor
    Itr() {}

    public boolean hasNext() {
      return cursor != arraySize;
    }

    @SuppressWarnings("unchecked")
    public T next() {
      checkForComodification();
      int i = cursor;
      if (i >= arraySize)
        throw new NoSuchElementException();
      Object[] elementData = DIYList.this.dataArray;
      if (i >= elementData.length)
        throw new ConcurrentModificationException();
      cursor = i + 1;
      return (T) elementData[lastRet = i];
    }

    @Override
    public boolean hasPrevious() {
      throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public T previous() {
      throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public int nextIndex() {
      return 0;
    }

    @Override
    public int previousIndex() {
      return 0;
    }

    public void remove() {
      if (lastRet < 0)
        throw new IllegalStateException();
      checkForComodification();

      try {
        DIYList.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
        expectedModCount = 0;
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    public void set(T t) {

    }

    @Override
    public void add(T t) {

    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
      Objects.requireNonNull(action);
      final int size = DIYList.this.arraySize;
      int i = cursor;
      if (i < size) {
        final Object[] es = dataArray;
        if (i >= es.length)
          throw new ConcurrentModificationException();
        for (; i < size && 0 == expectedModCount; i++)
          action.accept(elementAt(es, i));
        // update once at end to reduce heap write traffic
        cursor = i;
        lastRet = i - 1;
        checkForComodification();
      }
    }

    final void checkForComodification() {
      if (0 != expectedModCount)
        throw new ConcurrentModificationException();
    }
  }

  /**
   * An optimized version of AbstractList.ListItr
   */
  private class ListItr extends DIYList.Itr {
    ListItr(int index) {
      super();
      cursor = index;
    }

    public boolean hasPrevious() {
      return cursor != 0;
    }

    public int nextIndex() {
      return cursor;
    }

    public int previousIndex() {
      return cursor - 1;
    }

    @SuppressWarnings("unchecked")
    public T previous() {
      checkForComodification();
      int i = cursor - 1;
      if (i < 0)
        throw new NoSuchElementException();
      Object[] elementData = DIYList.this.dataArray;
      if (i >= elementData.length)
        throw new ConcurrentModificationException();
      cursor = i;
      return (T) elementData[lastRet = i];
    }

  }
}
