package 课程.实验课.实验2;

import java.util.Random;

public class _6_30 {
    static Random random = new Random();

    public static void main(String[] args) {
        int v1 = random.nextInt(1, 7);
        int v2 = random.nextInt(1, 7);
        int sum = v1 + v2;
        System.out.println("You rolled " + v1 + " + " + v2 + " = " + sum);
        switch (sum) {
            case 2, 3, 12 -> System.out.println("You lose");
            case 7, 11 -> System.out.println("You win");
            default -> {//确定一个点
                System.out.println("point is " + sum);
                int preSum = sum;
                while (true) {
                    int v3 = random.nextInt(1, 7);
                    int v4 = random.nextInt(1, 7);
                    int sum2 = v3 + v4;
                    System.out.println("You rolled " + v3 + " + " + v4 + " = " + sum2);
                    if (sum2 == 7) {
                        System.out.println("You lose");
                        break;
                    }
                    if (sum2 == preSum) {
                        System.out.println("You win");
                        break;
                    }
                    System.out.println("point is " + sum2);
                    preSum = sum2;
                }
            }

        }
    }
}
