package 课程.实验课.课堂练习;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class 文件IO课堂练习 {
    public static void main(String[] args) throws Exception {
        File file = new File("data.txt");
        /*
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("34 34\n24 45\n32 0\n23 12");
        writer.flush();
        */
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int n = 4;//4组数据
        for (int i = 0; i < n; i++) {
            String[] split = reader.readLine().split(" ");
            double a = Double.parseDouble(split[0]);
            double b = Double.parseDouble(split[1]);
            double m = a * b;
            double d = a / b;
            System.out.printf("%.2f * %.2f = %.2f\t", a, b, m);
            if (d == Double.POSITIVE_INFINITY) {
                System.out.printf("%.2f / %.2f = 没有计算结果\n", a, b);
            } else {
                System.out.printf("%.2f / %.2f = %.2f\n", a, b, d);
            }
        }
    }

}
