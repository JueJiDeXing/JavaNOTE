package 课程.线性代数;

import java.util.Arrays;

public class Matrix {

    long[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = new long[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

    }

    public Matrix(long[][] matrix) {
        this.matrix = matrix;
    }


    /**
     row1 - m*row2
     */
    public Matrix subRow(int row1, int row2, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[row1][i] = matrix[row1][i] + matrix[row2][i] * -m;
        }
        return this;
    }

    public Matrix addRow(int row1, int row2, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[row1][i] = matrix[row1][i] + matrix[row2][i] * m;
        }
        return this;
    }

    /**
     col1 - m*col2
     */
    public Matrix subCol(int col1, int col2, int m) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            matrix[i][col1] = matrix[i][col1] + matrix[i][col2] * -m;
        }
        return this;
    }

    public Matrix addCol(int col1, int col2, int m) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            matrix[i][col1] = matrix[i][col1] + matrix[i][col2] * m;
        }
        return this;
    }

    public Matrix mulRow(int row, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[row][i] = matrix[row][i] * m;
        }
        return this;
    }

    public Matrix mulCol(int col, int m) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            matrix[i][col] = matrix[i][col] * m;
        }
        return this;
    }

    /**
     转置
     */
    public Matrix transpose() {
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[j][i] = matrix[i][j];
            }
        }
        return this;
    }

    /**
     约分
     */
    public void simplification() {
        //对所有数据求分母的最大公因数
        long gcd = 1;
        for (long[] fractions : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                gcd = gcd(gcd, fractions[j]);
            }
        }
        //对所有数据进行约分
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] / gcd;
            }
        }
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    @Override
    public String toString() {
        simplification();
        StringBuilder sb = new StringBuilder();
        for (long[] mm : matrix) {
            sb.append(Arrays.toString(mm)).append("\n");
        }
        return sb.toString();
    }

    public Matrix swapRow(int row1, int row2) {
        long[] t = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = t;
        return this;
    }

    public Matrix swapCol(int col1, int col2) {
        for (int i = 0; i < matrix.length; i++) {
            long t = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = t;
        }
        return this;
    }

}
