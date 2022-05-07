package algorithms;

import java.util.ArrayList;
import java.util.List;

public class RepeatCombinationPermutation {

  private int elem;
  private int range;

  RepeatCombinationPermutation(int elem, int range) {
    this.elem = elem;
    this.range = range;
  }

  List<List<Integer>> permute() {
    int size = power(range, elem);
    int[] values = fillArray();
    List<List<Integer>> result = createSet(size);

    for (int i = 0; i < elem; i++) {
      int period = power(range, elem - i - 1);
      for (int j = 0; j < size; j++) {
        List<Integer> current = result.get(j);
        int index = j / period % range;
        current.add(i, values[index]);
      }
    }
    return result;
  }

  private int[] fillArray() {
    int[] result = new int[range];
    for (int i = 0; i < range; i++) {
      result[i] = i + 1;
    }
    return result;
  }

  private int power(int n, int p) {
    int result = 1;
    for (int i = 0; i < p; i++) {
      result *= n;
    }
    return result;
  }

  private List<List<Integer>> createSet(int size) {
    List<List<Integer>> result = new ArrayList<>(size);

    for (int i = 0; i < size; i++) {
      result.add(new ArrayList<>(elem));
    }
    return result;
  }

  public static void main(String[] args) {
    RepeatCombinationPermutation perm = new RepeatCombinationPermutation(3, 3);
    List<List<Integer>> permute = perm.permute();
    for (List<Integer> values : permute) {
      for (int value : values) {
        System.out.print(value + "   ");
      }
      System.out.println();
    }
  }
}
