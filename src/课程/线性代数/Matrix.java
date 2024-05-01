package 课程.线性代数;

import java.util.Arrays;

public class Matrix {
    int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    /**
     row1 - m*row2
     */
    public Matrix subRow(int row1, int row2, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[row1][i] -= m * matrix[row2][i];
        }
        return this;
    }

    /**
     col1 - m*col2
     */
    public Matrix subCol(int col1, int col2, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[i][col1] -= m * matrix[i][col2];
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] mm : matrix) {
            sb.append(Arrays.toString(mm)).append("\n");
        }
        return sb.toString();
    }

    public Matrix swapRow(int row1, int row2) {
        int[] t = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = t;
        return this;
    }

    public Matrix swapCol(int col1, int col2) {
        for (int i = 0; i < matrix.length; i++) {
            int t = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = t;
        }
        return this;
    }
}
