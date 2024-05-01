import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        int t = count(x);

        if (t == 0) {
            System.out.println(-1);
            return;
        }
        long A = x - 1, B = x + 1;
        while (count(A) >= t) A--;
        while (count(B) >= t) B++;
        System.out.println((x - A) * (B - x));
    }

    static int count(long x) {
        int t = 0;
        while (x > 0) {
            if (x % 10 % 2 == 0) t++;
            x /= 10;
        }
        return t;
    }
}


