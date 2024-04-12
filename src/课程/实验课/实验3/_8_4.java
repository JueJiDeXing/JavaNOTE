package 课程.实验课.实验3;

import java.util.Arrays;

public class _8_4 {
    public static void main(String[] args) {
        int[][] Employee = new int[][]{
                {2, 4, 3, 4, 5, 8, 8},
                {7, 3, 4, 3, 3, 4, 4},
                {3, 3, 4, 3, 3, 2, 2},
                {9, 3, 4, 7, 3, 4, 1},
                {3, 5, 4, 3, 6, 3, 8},
                {3, 4, 4, 6, 3, 4, 4},
                {3, 7, 4, 8, 3, 8, 4},
                {6, 3, 5, 9, 2, 7, 9},
        };
        Arrays.sort(Employee, (a, b) -> {
            int suma = 0, sumb = 0;
            for (int i = 0; i < 7; i++) {
                suma += a[i];
                sumb += b[i];
            }
            return Integer.compare(sumb, suma);
        });

        for (int[] employee : Employee) {
            System.out.println(Arrays.toString(employee));
        }
    }

}

