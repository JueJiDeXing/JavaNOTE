package 课程.实验课.课堂练习;

public class MyInteger {
    int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEven() {
        return value % 2 == 0;
    }

    public boolean isOdd() {
        return value % 2 == 1;
    }

    public boolean isPrime() {
        int sqrt = (int) Math.sqrt(value);
        for (int i = 2; i <= sqrt; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return value >= 2;
    }

    public static boolean isEven(int value) {
        return value % 2 == 0;
    }

    public static boolean isOdd(int value) {
        return value % 2 == 1;
    }

    public static boolean isPrime(int value) {
        int sqrt = (int) Math.sqrt(value);
        for (int i = 2; i <= sqrt; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return value >= 2;
    }

    public static boolean isEven(MyInteger integer) {
        return integer.getValue() % 2 == 0;
    }

    public static boolean isOdd(MyInteger integer) {
        return integer.getValue() % 2 == 1;
    }

    public static boolean isPrime(MyInteger integer) {
        int v = integer.getValue();
        int sqrt = (int) Math.sqrt(v);
        for (int i = 2; i <= sqrt; i++) {
            if (v % i == 0) {
                return false;
            }
        }
        return v >= 2;
    }

    public boolean equals(int value) {
        return this.value == value;
    }

    public boolean equals(MyInteger integer) {
        return this.value == integer.getValue();
    }

    public static int parseInt(char[] num) {
        int ans = 0;
        for (char c : num) {
            if (c < '0' || c > '9') {
                throw new RuntimeException("非数字字符");
            }
            ans = ans * 10 + (c - '0');
        }
        return ans;
    }

    public static int parseInt(String num) {
        int ans = 0;
        for (char c : num.toCharArray()) {
            if (c < '0' || c > '9') {
                throw new RuntimeException("非数字字符");
            }
            ans = ans * 10 + (c - '0');
        }
        return ans;
    }
}
