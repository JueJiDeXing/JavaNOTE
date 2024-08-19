package 课程.实验课.实验1;

public class _2_21 {
    public static void main(String[] args) {
        double base = 3.25 / 100 / 12;//月利率
        double res = 1000;
        for (int i = 0; i < 12; i++) {
            res *= (1 + base);
        }
        System.out.printf("%.2f", res);
    }
}
