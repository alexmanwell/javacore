package algorithms;

import org.junit.Test;

import java.util.*;

import static algorithms.PathHorse.MAX_DEPTH;
import static org.junit.Assert.assertEquals;

public class PathHorseTest {

  @Test
  public void testRightDownMove() {
    /*
     *   | 0 | 1 | 2 |
     *   -------------
     * 0 | 0 |   |   |
     * 1 |   |   | 1 |
     */
    PathHorse.Cell startCell = new PathHorse.Cell(0, 0, 0);
    PathHorse.Cell endCell = new PathHorse.Cell(2, 1, MAX_DEPTH);
    Set<PathHorse.Cell> disableCells = new HashSet<>();

    PathHorse horse = new PathHorse(3, startCell, endCell, disableCells);
    List<PathHorse.Cell> expected = Arrays.asList(
            new PathHorse.Cell(2, 1, 1),
            new PathHorse.Cell(0, 0, 0)
    );
    List<PathHorse.Cell> actual = horse.buildPath();

    System.out.println("Expected: " + expected + " Actual: " + actual);
    assertEquals(expected, actual);
  }

  @Test
  public void inputDataNumberOneTaskTest() {
    /*
     *   | 0 | 1 | 2 | 3 | 4 |
     *   ---------------------
     * 0 |   |   |   | 1 |   |
     * 1 |   | 0 | 3 |   |   |
     * 2 |   |   |   |   | 2 |
     * 3 |   | 0 |   |   |   |
     * 4 |   |   |   |   |   |
     */

    PathHorse.Cell startCell =  new PathHorse.Cell(1, 1, 0);
    PathHorse.Cell endCell =  new PathHorse.Cell(2, 1, MAX_DEPTH);
    int fieldSize = 5;

    PathHorse horse = new PathHorse(fieldSize, startCell, endCell);
    List<PathHorse.Cell> expected = Arrays.asList(
            new PathHorse.Cell(2, 1, 3),
            new PathHorse.Cell(4, 2, 2),
            new PathHorse.Cell(3, 0, 1),
            new PathHorse.Cell(1, 1, 0)
    );
    List<PathHorse.Cell> actual = horse.buildPath();

    System.out.println("Expected: " + expected + " Actual: " + actual);
    assertEquals(expected, actual);
  }

  @Test
  public void inputDataNumberTwoTaskTest() {
    /*
     *   | 0 | 1 | 2 | 3 | 4 |
     *   ---------------------
     * 0 | 0 |   |   | 5 |   |
     * 1 |   |   | x | x |   |
     * 2 |   | 1 | 4 |   |   |
     * 3 |   |   |   | 2 |   |
     * 4 |   | 3 |   |   |   |
     */

    PathHorse.Cell startCell =  new PathHorse.Cell(0, 0, 0);
    PathHorse.Cell endCell =  new PathHorse.Cell(3, 0, MAX_DEPTH);

    Set<PathHorse.Cell> disableCells = new HashSet<>();
    disableCells.add(new PathHorse.Cell(2, 1));
    disableCells.add(new PathHorse.Cell(3, 1));
    int fieldSize = 5;

    PathHorse horse = new PathHorse(fieldSize, startCell, endCell, disableCells);
    List<PathHorse.Cell> expected = Arrays.asList(
            new PathHorse.Cell(3, 0, 5),
            new PathHorse.Cell(2, 2, 4),
            new PathHorse.Cell(1, 4, 3),
            new PathHorse.Cell(3, 3, 2),
            new PathHorse.Cell(1, 2, 1),
            new PathHorse.Cell(0, 0, 0)
    );
    List<PathHorse.Cell> actual = horse.buildPath();

    System.out.println("Expected: " + expected + " Actual: " + actual);
    assertEquals(expected, actual);
  }

  @Test(expected = IllegalStateException.class)
  public void inputDataNumberThreeTaskTest() {
    /*
     *   | 0 | 1 | 2 | 3 | 4 |
     *   ---------------------
     * 0 | @ |   |   |   |   |
     * 1 |   |   | x |   |   |
     * 2 |   | x |   |   |   |
     * 3 |   |   |   |   |   |
     * 4 |   |   |   |   | @ |
     */

    PathHorse.Cell startCell =  new PathHorse.Cell(0, 0, 0);
    PathHorse.Cell endCell =  new PathHorse.Cell(4, 4, MAX_DEPTH);

    Set<PathHorse.Cell> disableCells = new HashSet<>();
    disableCells.add(new PathHorse.Cell(2, 1));
    disableCells.add(new PathHorse.Cell(1, 2));

    PathHorse horse = new PathHorse(5, startCell, endCell, disableCells);
    List<PathHorse.Cell> expected = Collections.emptyList();
    List<PathHorse.Cell> actual = horse.buildPath();

    assertEquals(expected, actual);
  }
}
