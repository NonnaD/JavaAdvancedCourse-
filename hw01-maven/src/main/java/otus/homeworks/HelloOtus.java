package otus.homeworks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Map;


public class HelloOtus {

  public static void main(String[] args) {
    Map<String, Integer> group2018 = ImmutableMap.of("Alan", 234, "Nonna", 123, "Alex", 324);
    Map<String, Integer> group2019 = ImmutableMap.of("Alan", 456, "Nonna", 123, "Alex", 989);
    HelloOtus compareInfo = new HelloOtus();
    System.out
        .println(compareInfo.compareCustomerGroupsInfo(group2018, group2019).entriesInCommon());
    System.out
        .println(compareInfo.compareCustomerGroupsInfo(group2018, group2019).entriesDiffering());
  }

  private MapDifference compareCustomerGroupsInfo(Map<String, Integer> firstGroup,
      Map<String, Integer> secondGroup) {

    MapDifference<String, Integer> difference = Maps.difference(firstGroup, secondGroup);
    return difference;
  }
}
