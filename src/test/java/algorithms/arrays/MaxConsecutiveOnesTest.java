package algorithms.arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxConsecutiveOnesTest {

  @Test
  public void testThreeTogetherElements() {
    int[] nums = new int[] {1, 1, 0, 1, 1, 1};
    MaxConsecutiveOnes mco = new MaxConsecutiveOnes();
    int actual = mco.findMaxConsecutiveOnes(nums);
    int expected = 3;

    assertEquals(expected, actual);
  }

  @Test
  public void testTwoTogetherElements() {
    int[] nums = new int[] {1, 0, 1, 1, 0, 1};

    MaxConsecutiveOnes mco = new MaxConsecutiveOnes();
    int actual = mco.findMaxConsecutiveOnes(nums);
    int expected = 2;

    assertEquals(expected, actual);
  }
}