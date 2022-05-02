package algorithms;

/*
Путь коня
    Дана шахматная доска, состоящая из n × n клеток, несколько из них вырезано.
    Провести ходом коня через невырезанные клетки путь минимальной длины из одной клетки в другую.

        Входные данные
        В первой строке задано число n (2 ≤ n ≤ 50). В следующих n строках содержится по n символов.
        Символом # обозначена вырезанная клетка, точкой - невырезанная клетка,
                 @ - начальная и конечная клетки пути коня (таких символов два).

        Выходные данные
        Если путь построить невозможно, то вывести "Impossible".
        В противном случае вывести такую же карту, как и на входе,
        но пометить все промежуточные положения коня символом @.
*/

import java.util.*;

public class PathHorse {
  static final int MAX_DEPTH = Integer.MAX_VALUE;

  private int fieldSize;
  private Cell startCell;
  private Cell endCell;
  private Set<Cell> disableCells;
  private Cell[][] field;

  PathHorse(int fieldSize, Cell startCell, Cell endCell) {
    this(fieldSize, startCell, endCell, new HashSet<>());
  }

  PathHorse(int fieldSize, Cell startCell, Cell endCell, Set<Cell> disableCells) {
    this.fieldSize = fieldSize;
    this.startCell = startCell;
    this.endCell = endCell;
    this.disableCells = disableCells;
    this.field = new Cell[fieldSize][fieldSize];

    initField(startCell, endCell, disableCells);
  }

  private enum Offset {

    LEFT_UP(-2, -1),
    LEFT_DOWN(-2, 1),
    RIGHT_UP(2, -1),
    RIGHT_DOWN(2, 1),
    UP_LEFT(-1, -2),
    UP_RIGHT(1, -2),
    DOWN_LEFT(-1, 2),
    DOWN_RIGHT(1, 2);

    private int x;
    private int y;

    Offset(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class Cell {
    private int x;
    private int y;
    private int depth;
    private Cell lastStep;

    Cell(int x, int y) {
      this(x, y, 0, null);
    }

    Cell(int x, int y, int depth) {
      this(x, y, depth, null);
    }

    Cell(int x, int y, int depth, Cell lastStep) {
      this.x = x;
      this.y = y;
      this.depth = depth;
      this.lastStep = lastStep;
    }

    int getDepth() {
      return depth;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Cell cell = (Cell) o;

      return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
      return 31 * x + y;
    }

    @Override
    public String toString() {
      return " " + depth + " [" + x + ", " + y + "]";
    }
  }

  private void initField(Cell start, Cell end, Set<Cell> disables) {
    for (Cell disable : disables) {
      field[disable.x][disable.y] = null;
    }
    field[start.x][end.y] = start;
    field[end.x][end.y] = end;
  }

  private Cell buildWave() {
    Queue<Cell> wave = new LinkedList<>();
    wave.add(startCell);

    Cell cell = wave.poll();
    while (cell != null && !this.endCell.equals(cell)) {
      for (Offset offset : Offset.values()) {
        Cell next = move(cell, offset);
        if (next != null) {
          Cell position = field[next.x][next.y];
          int depth = (position != null) ? position.depth : MAX_DEPTH;
          if (depth > cell.depth) {
            field[next.x][next.y] = next;
            wave.add(next);
          }
        }
      }
      cell = wave.poll();
    }
    endCell = cell;
    return cell;
  }

  private Cell move(Cell current, Offset offset) {
    Cell next = null;
    int nextX = current.x + offset.x;
    int nextY = current.y + offset.y;
    if (isValid(nextX, nextY)) {
      next = new Cell(nextX, nextY, current.depth + 1, current);
      if (this.disableCells.contains(next)) {
        next = null;
      }
    }
    return next;
  }

  private boolean isValid(int x, int y) {
    return (x >= 0 && x < fieldSize) && (y >= 0 && y < fieldSize);
  }

  List<Cell> buildPath() {
    boolean isWave = buildWave() != null;
    if (!isWave) {
      throw new IllegalStateException("Impossible build path to the cells: [" + endCell + "]");
    }

    List<Cell> path = new ArrayList<>();
    Cell currentCell = this.endCell;
    while (!currentCell.equals(this.startCell)) {
      path.add(currentCell);
      currentCell = currentCell.lastStep;
    }
    path.add(this.startCell);

    return path;
  }

  String printPathField(List<Cell> path) {
    StringBuilder b = new StringBuilder();
    Cell[][] field = new Cell[fieldSize][fieldSize];
    for (int row = 0; row < fieldSize; row++) {
      for (int col = 0; col < fieldSize; col++) {
        field[row][col] = null;
      }
    }

    b.append(debugTitlePrint()).append("\n");
    for (int col = 0; col < fieldSize; col++) {
      b.append("       ").append(col).append("  |");
      for (int row = 0; row < fieldSize; row++) {
        for (Cell cell : path) {
          if (row == cell.x && col == cell.y) {
            field[row][col] = cell;
            b.append(field[row][col]).append("  |");
          }
        }
        for (Cell disable : disableCells) {
          if (row == disable.x && col == disable.y) {
            field[row][col] = disable;
            b.append("     X   ").append("  |");
          }
        }
        if (field[row][col] == null) {
          b.append("           |");
        }
      }
      b.append("\n");
    }
    return b.toString();
  }

  private StringBuilder debugTitlePrint() {
    StringBuilder b = new StringBuilder();
    b.append("\n");
    b.append("   row/col|");
    for (int row = 0; row < fieldSize; row++) {
      b.append("     ").append(row).append("     |");
    }
    b.append("\n").append("   --------");
    for (int col = 0; col < fieldSize; col++) {
      b.append("------------");
    }
    return b;
  }
}