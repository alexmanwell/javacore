package algorithms.arrays;

import java.util.Scanner;

public class Matrix {

  static int[][] fillDiagonals(int[][] matrix) {
    int size = matrix.length;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (i == j || j == size - 1 - i) {
          matrix[i][j] = 1;
        } else {
          matrix[i][j] = 0;
        }
      }
    }

    return matrix;
  }

  static int[][] fillCross(int[][] matrix) {
    int size = matrix.length;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (i == size / 2 || j == size / 2 || i == j || i + j == size - 1) {
          matrix[i][j] = 1;
        } else {
          matrix[i][j] = 0;
        }
      }
    }

    return matrix;
  }

  static int[][] fillHourglass(int[][] matrix) {
    int size = matrix.length;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if ((j >= i && j <= size - i - 1) || (j <= i && j >= size - i - 1)) {
          matrix[i][j] = 1;
        } else {
          matrix[i][j] = 0;
        }
      }
    }

    return matrix;
  }

  static void printValues(int[][] values) {
    for (int[] row : values) {
      for (int cell : row) {
        System.out.print(cell + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter size of the matrix ( size must be odd):");
    int size = scanner.nextInt();
    int[][] matrix = new int[size][size];

    int[][] data = fillDiagonals(matrix);
    System.out.println("Print diagonally:");
    printValues(data);

    data = fillCross(matrix);
    System.out.println("Print cross:");
    printValues(data);

    data = fillHourglass(matrix);
    System.out.println("Print hourglass:");
    printValues(data);
  }
}
