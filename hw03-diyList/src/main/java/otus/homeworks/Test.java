package otus.homeworks;

import java.util.Collections;
import java.util.List;

public class Test {

  public static void main(String[] args) {

    List<String> testList = new DIYList<>();
    List<String> destination;

    //Verify DIYList<> works for more than 20 elements
    for(int i = 0; i < 25; i++){
      testList.add(i + " element");
    }
   System.out.println(testList.get(23));

    //Verify .addAll method works for DIYList class
    Collections.addAll(testList, "new Element", "new Element 2", "new Element 3 ");
    System.out.println(testList);


    //Verify .copy method works for DIYList class
    destination = new DIYList<>(testList);
    System.out.println(destination.size());
    Collections.copy(destination, testList);
    System.out.println(destination);


    //Verify sorting method
    Collections.sort(testList, new DIYComparator());
    System.out.println(testList);
    }
}
