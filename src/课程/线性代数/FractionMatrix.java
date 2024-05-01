package 课程.线性代数;

import 课程.数值计算.Fraction;

import java.util.Arrays;

public class FractionMatrix {


    Fraction[][] matrix;

    public FractionMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        this.matrix = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = new Fraction(matrix[i][j]);
            }
        }
    }

    /**
     row1 - m*row2
     */
    public FractionMatrix subRow(int row1, int row2, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[row1][i] = matrix[row1][i].add(matrix[row2][i].mul(-m));
        }
        return this;
    }

    /**
     col1 - m*col2
     */
    public FractionMatrix subCol(int col1, int col2, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[i][col1] = matrix[i][col1].add(matrix[i][col2].mul(-m));
        }
        return this;
    }

    public void simplification() {
        //对所有数据求分母的最小公倍数
        long lcm = 1;
        for (Fraction[] fractions : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                lcm = lcm(lcm, fractions[j].denominator);
            }
        }
        //对所有数据进行约分
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j].mul(lcm);
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
        for (Fraction[] mm : matrix) {
            sb.append(Arrays.toString(mm)).append("\n");
        }
        return sb.toString();
    }

    public FractionMatrix swapRow(int row1, int row2) {
        Fraction[] t = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = t;
        return this;
    }

    public FractionMatrix swapCol(int col1, int col2) {
        for (int i = 0; i < matrix.length; i++) {
            Fraction t = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = t;
        }
        return this;
    }

}
