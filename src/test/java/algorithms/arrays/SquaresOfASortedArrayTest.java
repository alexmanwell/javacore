package algorithms.arrays;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SquaresOfASortedArrayTest {

  @Test
  public void testSimpleSquares() {
    int[] nums = new int[]{-4, -1, 0, 3, 10};
    SquaresOfASortedArray squares = new SquaresOfASortedArray();
    int[] actuals = squares.sort(nums);
    int[] expecteds = new int[] {0, 1, 9, 16, 100};

    assertArrayEquals(expecteds, actuals);
  }

  @Test
  public void testSimpleSquaresSecond() {
    int[] nums = new int[]{-7, -3, 2, 3, 11};
    SquaresOfASortedArray squares = new SquaresOfASortedArray();
    int[] actuals = squares.sort(nums);
    int[] expecteds = new int[] {4, 9, 9, 49, 121};

    assertArrayEquals(expecteds, actuals);
  }

  @Test
  public void testSimpleSquaresThird() {
    int[] nums = new int[]{-15, -8, 2, 3, 1};
    SquaresOfASortedArray squares = new SquaresOfASortedArray();
    int[] actuals = squares.sort(nums);
    int[] expecteds = new int[] {1, 4, 9, 64, 225};

    assertArrayEquals(expecteds, actuals);
  }
}
