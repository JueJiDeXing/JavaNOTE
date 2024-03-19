package 课程.实验课;

import java.util.Scanner;

public class FindScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生人数:");
        int n = sc.nextInt();
        if (n <= 0) throw new RuntimeException("学生人数必须大于0");

        int firstScore = -1, secondScore = -1;
        String firstName = "null", secondName = "null";
        for (int i = 0; i < n; i++) {
            System.out.println("请输入下一个学生名字和分数:");
            String name = sc.next();
            int score = sc.nextInt();
            if (score < 0) throw new RuntimeException("分数不能为负数");
            if (score > firstScore) {
                secondScore = firstScore;
                secondName = firstName;
                firstScore = score;
                firstName = name;
            } else if (score > secondScore) {
                secondScore = score;
                secondName = name;
            }
        }
        System.out.println("最高分为" + firstScore + ", 该学生是" + firstName);
        if (secondScore == -1) {
            System.out.println("无第二高分");
        } else {
            System.out.println("第二高分为" + secondScore + ", 该学生是" + secondName);
        }
    }
}
