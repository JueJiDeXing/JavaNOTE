package 课程.线性代数;

import 课程.数值计算.Fraction;

import java.util.Arrays;

public class FractionMatrix {

    Fraction[][] matrix;

    public FractionMatrix(Fraction[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        this.matrix = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = new Fraction(matrix[i][j]);
            }
        }
    }

    public FractionMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        this.matrix = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = new Fraction(matrix[i][j]);
            }
        }
    }

    public FractionMatrix(double[][] matrix) {
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
     row1 + m*row2
     */
    public FractionMatrix addRow(int row1, int row2, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[row1][i] = matrix[row1][i].add(matrix[row2][i].mul(m));
        }
        return this;
    }

    /**
     col1 - m*col2
     */
    public FractionMatrix subCol(int col1, int col2, int m) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            matrix[i][col1] = matrix[i][col1].add(matrix[i][col2].mul(-m));
        }
        return this;
    }

    public FractionMatrix addCol(int col1, int col2, int m) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            matrix[i][col1] = matrix[i][col1].add(matrix[i][col2].mul(m));
        }
        return this;
    }

    public FractionMatrix mulRow(int row, int m) {
        int length = matrix[0].length;
        for (int i = 0; i < length; i++) {
            matrix[row][i] = matrix[row][i].mul(m);
        }
        return this;
    }

    public FractionMatrix mulCol(int col, int m) {
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            matrix[i][col] = matrix[i][col].mul(m);
        }
        return this;
    }

    /**
     矩阵乘法
     */
    public FractionMatrix mul(Fraction[][] other) {
        int n = matrix.length, m = other[0].length, p = matrix[0].length;
        Fraction[][] ans = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = ans[i][j].add(matrix[i][k].mul(other[k][j]));
                }
            }
        }
        return new FractionMatrix(ans);
    }

    /**
     矩阵乘法
     */
    public FractionMatrix mul(int[][] other) {
        int n = matrix.length, m = other[0].length, p = matrix[0].length;
        Fraction[][] ans = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = ans[i][j].add(matrix[i][k].mul(other[k][j]));
                }
            }
        }
        return new FractionMatrix(ans);
    }

    /**
     矩阵乘法
     */
    public Fraction[][] mul(Fraction[][] matrix, Fraction[][] other) {
        int n = matrix.length, m = other[0].length, p = matrix[0].length;
        Fraction[][] ans = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = ans[i][j].add(matrix[i][k].mul(other[k][j]));
                }
            }
        }
        return ans;
    }

    /**
     快速幂
     */
    public FractionMatrix pow(long n) {
        int len = matrix.length;
        if (len != matrix[0].length) throw new RuntimeException("矩阵必须为方阵");
        FractionMatrix ans = new FractionMatrix(new Fraction[len][len]);
        for (int i = 0; i < len; i++) ans.matrix[i][i] = new Fraction(1);
        while (n > 0) {
            if ((n & 1) == 1) ans = ans.mul(matrix);
            matrix = mul(matrix, matrix);
            n >>= 1;
        }
        return ans;
    }

    /**
     矩阵乘法
     */
    public FractionMatrix mul(long[][] other) {
        int n = matrix.length, m = other[0].length, p = matrix[0].length;
        Fraction[][] ans = new Fraction[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = ans[i][j].add(matrix[i][k].mul(other[k][j]));
                }
            }
        }
        return new FractionMatrix(ans);
    }

    /**
     矩阵乘法
     */
    public FractionMatrix mul(FractionMatrix other) {
        int n = matrix.length, m = other.matrix[0].length, p = matrix[0].length;
        Fraction[][] ans = new Fraction[n][m];
        for (Fraction[] a : ans) Arrays.setAll(a, k -> new Fraction(0));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = ans[i][j].add(matrix[i][k].mul(other.matrix[k][j]));
                }
            }
        }
        return new FractionMatrix(ans);
    }

    /**
     转置
     */
    public FractionMatrix transpose() {
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[j][i] = matrix[i][j];
            }
        }
        return this;
    }

    /**
     求行列式(方阵)
     */
    public double det() {
        if (!isSquare()) throw new RuntimeException("矩阵必须为方阵");
        return calculateDeterminant(matrix).getValue();
    }

    /**
     是否为方阵
     */
    public boolean isSquare() {
        return matrix.length == matrix[0].length;
    }

    // 计算 n 阶行列式
    public Fraction calculateDeterminant(Fraction[][] matrix) {
        int n = matrix.length;
        if (n == 1) return matrix[0][0];
        if (n == 2) {// 当 n 等于 2 时,直接计算 2x2 行列式
            return matrix[0][0].mul(matrix[1][1]).sub(matrix[0][1].mul(matrix[1][0]));
        }
        Fraction ans = new Fraction(0);
        // 按第一行展开
        for (int i = 0, j = 0; j < n; j++) {
            Fraction[][] subMatrix = createSubMatrix(matrix, i, j);// 创建一个 n-1 阶子矩阵，删除 第 1 行和第 j 列
            //行号1,列号j+1,(-1)^(1+j+1)=(-1)^j
            ans = ans.add(
                    (matrix[0][j].mul(calculateDeterminant(subMatrix))).mul(j % 2 == 0 ? 1 : -1)
            );   // 计算子矩阵的行列式，并根据排列的符号乘以相应的系数
        }
        return ans;
    }

    /**
     创建子矩阵

     @param rowToRemove,colToRemove 要删除的行和列
     */
    public Fraction[][] createSubMatrix(Fraction[][] matrix, int rowToRemove, int colToRemove) {
        int n = matrix.length, m = matrix[0].length;
        Fraction[][] subMatrix = new Fraction[n - 1][m - 1];
        // 复制除了要删除的行和列之外的其他元素
        int is = 0, js = 0;
        for (int i = 0; i < n; i++) {
            if (i == rowToRemove) continue;
            for (int j = 0; j < m; j++) {
                if (j != colToRemove) {
                    subMatrix[is][js] = matrix[i][j];
                    js++;
                }
            }
            is++;
            js = 0;
        }
        return subMatrix;
    }

    /**
     创建子矩阵

     @param rowToRemove,colToRemove 要删除的行和列
     */
    public Fraction[][] createSubMatrix(int rowToRemove, int colToRemove) {
        int n = matrix.length, m = matrix[0].length;
        Fraction[][] subMatrix = new Fraction[n - 1][m - 1];
        // 复制除了要删除的行和列之外的其他元素
        int is = 0, js = 0;
        for (int i = 0; i < n; i++) {
            if (i == rowToRemove) continue;
            for (int j = 0; j < m; j++) {
                if (j != colToRemove) {
                    subMatrix[is][js] = matrix[i][j];
                    js++;
                }
            }
            is++;
            js = 0;
        }
        return subMatrix;
    }

    /**
     约分
     */
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

    /**
     计算代数余子式

     @param r,c A[r][c]
     */
    public double calA(int r, int c) {
        if (!isSquare()) throw new RuntimeException("矩阵必须为方阵");
        return calculateDeterminant(createSubMatrix(matrix, r, c)).mul((r + c) % 2 == 0 ? 1 : -1).getValue();
    }

    /**
     计算一行的代数余子式之和

     @param r A[r][0]+A[r][1]+...+A[r][n-1]
     */
    public double calARow(int r) {
        if (!isSquare()) throw new RuntimeException("矩阵必须为方阵");
        FractionMatrix temp = new FractionMatrix(matrix);
        temp.setRow(r, 1);
        return temp.det();
    }

    public FractionMatrix setRow(int r, int v) {
        for (int c = 0; c < this.matrix[0].length; c++) {
            this.matrix[r][c] = new Fraction(v);
        }
        return this;
    }

    /**
     计算一行的代数余子式之和

     @param r    A[r][0]+A[r][1]+...+A[r][n-1]
     @param vals 系数
     */
    public double calARow(int r, int... vals) {
        if (vals.length != matrix[0].length) throw new RuntimeException("系数不全");
        if (!isSquare()) throw new RuntimeException("矩阵必须为方阵");
        FractionMatrix temp = new FractionMatrix(matrix);
        temp.setRow(r, vals);
        return temp.det();
    }

    public FractionMatrix setRow(int r, int... vals) {
        if (vals.length != this.matrix[0].length) throw new RuntimeException("系数不全");
        for (int c = 0; c < this.matrix[0].length; c++) {
            this.matrix[r][c] = new Fraction(vals[c]);
        }
        return this;
    }

    /**
     计算一列的代数余子式之和
     */
    public double calACol(int c) {
        if (!isSquare()) throw new RuntimeException("矩阵必须为方阵");
        FractionMatrix temp = new FractionMatrix(matrix);
        temp.setCol(c, 1);
        return temp.det();
    }

    public FractionMatrix setCol(int c, int v) {
        for (int r = 0; r < this.matrix.length; r++) {
            this.matrix[r][c] = new Fraction(v);
        }
        return this;
    }

    /**
     计算一列的代数余子式之和
     */
    public double calACol(int c, int... vals) {
        if (vals.length != matrix.length) throw new RuntimeException("系数不全");
        if (!isSquare()) throw new RuntimeException("矩阵必须为方阵");
        FractionMatrix temp = new FractionMatrix(matrix);
        temp.setCol(c, vals);
        return temp.det();
    }

    public FractionMatrix setCol(int c, int... vals) {
        for (int r = 0; r < this.matrix.length; r++) {
            this.matrix[r][c] = new Fraction(vals[r]);
        }
        return this;
    }

    public FractionMatrix divRow(int row, int m) {
        for (int c = 0; c < matrix[0].length; c++) {
            matrix[row][c] = matrix[row][c].div(m);
        }
        return this;
    }

    public FractionMatrix divCol(int col, int m) {
        for (int r = 0; r < matrix.length; r++) {
            matrix[r][col] = matrix[r][col].div(m);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FractionMatrix matrix1 = (FractionMatrix) o;
        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
}
