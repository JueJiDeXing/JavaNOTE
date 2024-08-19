package 课程.实验课.实验5;

import java.util.Random;

public class 最大的行和列 {
    public static void main(String[] args) {
        int n = 4;
        // 随机生成0/1矩阵
        Random random = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }
        System.out.println("生成的矩阵为: ");
        print(matrix);
        // 寻找最大的行和列
        int[] rowCnt = new int[n], colCnt = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowCnt[i] += matrix[i][j];// 统计每行的和
                colCnt[j] += matrix[i][j];// 统计每列的和
            }
        }

        int maxRowId = 0, maxColId = 0;
        for (int i = 1; i < n; i++) {
            if (rowCnt[i] > rowCnt[maxRowId]) maxRowId = i;//查找最大的行和列
            if (colCnt[i] > colCnt[maxColId]) maxColId = i;//查找最大的行和列
        }
        System.out.println("The largest row index: " + maxRowId + "\n" + "The largest column index: " + maxColId);
    }

    private static void print(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

}
