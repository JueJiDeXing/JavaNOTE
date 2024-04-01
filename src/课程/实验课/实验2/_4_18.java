package 课程.实验课.实验2;

import java.util.*;

public class _4_18 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter two characters:");
        String s = sc.next();
        if (s.length() != 2) {
            System.out.println("Invalid input");
            return;
        }
        char source = s.charAt(0);
        int grade = s.charAt(1) - '0';
        if ((source != 'M' && source != 'C' && source != 'I') || (grade <= 0 || grade > 4)) {
            System.out.println("Invalid input");
            return;
        }
        if (source == 'M') {
            System.out.print("Mathematics ");
        } else if (source == 'C') {
            System.out.print("Computer Science ");
        } else {
            System.out.print("information technology ");
        }
        if (grade == 1) {
            System.out.println("Freshman");
        } else if (grade == 2) {
            System.out.println("sophomore");
        } else if (grade == 3) {
            System.out.println("junior");
        } else {
            System.out.println("senior");
        }
    }
}
