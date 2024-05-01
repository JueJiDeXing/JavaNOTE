package 课程.线性代数;

public class 求秩 {

    public static void main(String[] args) {

        Matrix matrix = new Matrix(new int[][]{
                {3, -3, -1, 5},
                {1, -2, -1, 2},
                {5, -1, 5, 3},
                {-2, 2, 3, -4},
        });

        matrix.swapRow(0, 1);
        System.out.println(matrix);
        matrix.subRow(1, 0, 3)
                .subRow(2, 0, 5)
                .subRow(3, 0, -2);
        System.out.println(matrix);
        matrix.subRow(1, 3, -1)
                .subRow(2,1,9)
                .subRow(3,1,-2);
        System.out.println(matrix);

    }

}
