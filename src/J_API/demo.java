package J_API;

import java.util.ArrayList;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入一个整数");
        int num = scanner.nextInt();
        int temp_num = num;
        int last_num;
        int sum = 0;
        ArrayList<Integer> arrays = new ArrayList<>();
        while (num > 0) {
            last_num = num % 10;
            arrays.add(last_num);
            num = num / 10;
        }
        int length = arrays.size();
        for (Integer array : arrays) {
            sum += Math.pow(array, length);
        }
        System.out.println(sum);
        if (sum == temp_num) {
            System.out.println("是自幂数");
        }
    }
}
