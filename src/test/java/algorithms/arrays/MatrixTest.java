package algorithms.arrays;

import static algorithms.arrays.Matrix.*;
import static org.junit.Assert.*;

public class MatrixTest {

  public static void main(String[] args) {
    testDiagonals();
    testCross();
    testHourglass();
  }

  private static void testDiagonals() {
    int size = 7;
    int[][] matrix = new int[size][size];

    int[][] actual = fillDiagonals(matrix);
    int[][] expected = new int[][]{
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
    };
    assertEquals("TaskCh12N023Test.testDiagonals", expected, actual);
  }

  private static void testCross() {
    int size = 7;
    int[][] matrix = new int[size][size];

    int[][] actual = fillCross(matrix);
    int[][] expected = new int[][]{
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 1}
    };
    assertEquals("TaskCh12N023Test.testCross", expected, actual);
  }

  private static void testHourglass() {
    int size = 7;
    int[][] matrix = new int[size][size];

    int[][] actual = fillHourglass(matrix);
    int[][] expected = new int[][]{
            {1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1}
    };
    assertEquals("TaskCh12N023Test.testHourglass", expected, actual);
  }
}