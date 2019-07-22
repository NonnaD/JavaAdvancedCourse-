package otus.homeworks;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DIYList<T> extends DIYRandomAccess
    implements List<T> {

  private Object[] defaultArray = {};
  private Object[] emptyArray = {};
  private int arraySize;

  public DIYList() {
    this.defaultArray = emptyArray;
  }

  public DIYList(Collection<? extends T> e) {
    defaultArray = e.toArray();
    if ((arraySize = defaultArray.length) != 0) {
      if (defaultArray.getClass() != Object[].class) {
        defaultArray = Arrays.copyOf(defaultArray, arraySize, Object[].class);
      }
    } else {
      this.defaultArray = emptyArray;
    }
  }

  @Override
  public String toString() {
    return Arrays.toString(defaultArray);
  }

  @Override
  public int size() {
    return arraySize;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(defaultArray, arraySize);
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    return null;
  }

  @Override
  public boolean add(T t) {
    add(arraySize, defaultArray, t);
    return true;
  }

  private void add(int s, Object[] array, T element) {
    if (s == array.length || s < 0) {
      array = extendArray(arraySize + 1);
      array[s] = element;
      arraySize++;
    } else {
      array[s] = element;
      arraySize++;
    }
  }


  private Object[] extendArray(int newSize) {
    return defaultArray = Arrays.copyOf(defaultArray,
        newSize);
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    return false;
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public T get(int index) {
    return (T) defaultArray[index];
  }

  @Override
  public T set(int index, T element) {
    return null;
  }

  @Override
  public void add(int index, T element) {
  }

  @Override
  public T remove(int index) {
    return null;
  }

  @Override
  public int indexOf(Object o) {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator<T> listIterator() {
    return null;
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return null;
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return null;
  }
}
