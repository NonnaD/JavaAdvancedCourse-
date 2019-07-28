package otus.homeworks;

import java.util.Comparator;

public class DIYStringComparator<T extends String> implements Comparator<T> {

  @Override
  public int compare(String a, String b) {
    return a.compareTo(b);
  }
}
