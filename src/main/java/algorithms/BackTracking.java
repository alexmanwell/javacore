package algorithms;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class BackTracking {

  public static Set<int[]> backTracking(int... limits) {
    Set<int[]> values = new LinkedHashSet<>();
    int[] arr = new int[limits.length];
    int j = 0;
    arr[0] = -1;
    while (j >= 0) {
      while (arr[j] < limits[j] - 1) {
        arr[j]++;
        if (j == limits.length - 1) {
          values.add(Arrays.copyOf(arr, arr.length));
        } else {
          j++;
          arr[j] = -1;
        }
      }
      j--;
    }
    return values;
  }

  public static void main(String[] args) {
    Set<int[]> back = backTracking(2, 3);
    for (int[] s : back) {
      System.out.println(Arrays.toString(s));
    }
  }
}