package algorithms;

import org.junit.Test;

import java.util.*;

import static algorithms.BackTracking.backTracking;
import static org.junit.Assert.assertEquals;

public class BackTrackingTest {

  @Test
  public void testBackTracking1() {
    assertEquals(strings(set(array(0), array(1), array(2), array(3))),  strings(backTracking(4)));
  }

  @Test
  public void testBackTracking2() {
    assertEquals(strings(set(array(0, 0))), strings(backTracking(1, 1)));
  }

  @Test
  public void testBackTracking21() {
    assertEquals(strings(set(array(0, 0), array(0, 1), array(1, 0), array(1, 1))), strings(backTracking(2, 2)));
  }

  @Test
  public void testBackTracking22() {
    assertEquals(strings(set(array(0, 0), array(1, 0))), strings(backTracking(2, 1)));
  }

  @Test
  public void testBackTracking3() {
    assertEquals(strings(set(array(0, 0, 0))), strings(backTracking(1, 1, 1)));
  }

  @Test
  public void testBackTracking4() {
    assertEquals(strings(set(array(0, 0, 0), array(0, 0, 1))), strings(backTracking(1, 1, 2)));
  }

  @Test
  public void testBackTracking41() {
    assertEquals(strings(set(array(0, 0, 0), array(0, 0, 1), array(0, 0, 2))), strings(backTracking(1, 1, 3)));
  }

  private int[] array(int... a) {
    return a;
  }

  private static Set<int[]> set(int[]... elems) {
    final LinkedHashSet<int[]> set = new LinkedHashSet<>();
    Collections.addAll(set, elems);
    return set;
  }

  private static Set<String> strings(Collection<int[]> elems) {
    final LinkedHashSet<String> set = new LinkedHashSet<>();
    for (int[] elem : elems) {
      set.add(Arrays.toString(elem));
    }
    return set;
  }
}