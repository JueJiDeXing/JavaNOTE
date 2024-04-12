package 课程.实验课;

import java.util.Random;

public class 二维数组最多1 {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 4;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        int[] rowCnt = new int[n], colCnt = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowCnt[i] += matrix[i][j];
                colCnt[j] += matrix[i][j];
            }
        }

        int maxRow = 0, maxCol = 0;
        for (int i = 1; i < n; i++) {
            if (rowCnt[i] > rowCnt[maxRow]) maxRow = i;
            if (colCnt[i] > colCnt[maxCol]) maxCol = i;
        }
        System.out.println("The largest row index: " + maxRow + "\n" + "The largest column index: " + maxCol);
    }
}
