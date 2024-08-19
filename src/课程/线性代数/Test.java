package 课程.线性代数;

public class Test {

    public static void main(String[] args) {

        FractionMatrix A = new FractionMatrix(
                new int[][]{
                        {1, -2, -6},
                        {1, 4,3},
                        {2, 2, -3}
                }
        );

        System.out.println(A.det());

    }
}
