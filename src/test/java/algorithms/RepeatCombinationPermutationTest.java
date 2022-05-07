package algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepeatCombinationPermutationTest {

  @Test
  public void simpleCombinationTest() {
    RepeatCombinationPermutation rcp = new RepeatCombinationPermutation(1, 1);
    List<List<Integer>> actual = rcp.permute();
    List<List<Integer>> expected = new ArrayList<>(1);
    expected.add(Collections.singletonList(1));

    assertEquals(expected, actual);
  }

  @Test
  public void complexCombinationTwoElemTwoValueTest() {
    RepeatCombinationPermutation rcp = new RepeatCombinationPermutation(2, 2);
    List<List<Integer>> actual = rcp.permute();
    List<List<Integer>> expected = new ArrayList<>(4);
    expected.add(Arrays.asList(1, 1));
    expected.add(Arrays.asList(1, 2));
    expected.add(Arrays.asList(2, 1));
    expected.add(Arrays.asList(2, 2));

    assertEquals(expected, actual);
  }

  @Test
  public void complexCombinationTwoElemThreeValueTest() {
    RepeatCombinationPermutation rcp = new RepeatCombinationPermutation(2, 3);
    List<List<Integer>> actual = rcp.permute();
    List<List<Integer>> expected = new ArrayList<>(9);
    expected.add(Arrays.asList(1, 1));
    expected.add(Arrays.asList(1, 2));
    expected.add(Arrays.asList(1, 3));
    expected.add(Arrays.asList(2, 1));
    expected.add(Arrays.asList(2, 2));
    expected.add(Arrays.asList(2, 3));
    expected.add(Arrays.asList(3, 1));
    expected.add(Arrays.asList(3, 2));
    expected.add(Arrays.asList(3, 3));

    assertEquals(expected, actual);
  }
}
