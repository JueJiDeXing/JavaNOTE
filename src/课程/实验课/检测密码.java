package 课程.实验课;

import java.util.*;

public class 检测密码 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String password = sc.next();
        //密码至少8位
        if (password.length() < 8) {
            System.out.println("Invalid Password");
            return;
        }
        //密码只能有数字和字母
        int numCount = 0;
        for (char ch : password.toCharArray()) {
            if ('0' <= ch && ch <= '9') {//是数字
                numCount++;
                continue;
            }
            if (!(('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z'))) {//不是数字也不是字母
                System.out.println("Invalid Password");
                return;
            }
        }
        if (numCount >= 2) {
            System.out.println("Valid Password");
        } else {
            System.out.println("Invalid Password");
        }

    }
}
