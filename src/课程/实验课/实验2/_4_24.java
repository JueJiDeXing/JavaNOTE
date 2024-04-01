package 课程.实验课.实验2;

import java.util.*;

public class _4_24 {
    /*
    Enter the first city: Chicago
    Enter the second city: Los Angeles
    Enter the third city: Atlanta
    The three cities in alphabetical order are Atlanta Chicago Los Angeles
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first city: ");
        String c1 = sc.nextLine();
        System.out.print("Enter the second city: ");
        String c2 = sc.nextLine();
        System.out.print("Enter the third city: ");
        String c3 = sc.nextLine();
        if (c1.compareTo(c2) > 0) {
            String temp = c1;
            c1 = c2;
            c2 = temp;
        }
        if (c2.compareTo(c3) > 0) {
            String temp = c2;
            c2 = c3;
            c3 = temp;
        }
        if (c1.compareTo(c2) > 0) {
            String temp = c1;
            c1 = c2;
            c2 = temp;
        }
        System.out.println("three cities in alphabetical order are " + c1 + " " + c2 + " " + c3);
    }
}
