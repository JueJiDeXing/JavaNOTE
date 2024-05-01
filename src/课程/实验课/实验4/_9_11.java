package 课程.实验课.实验4;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;

public class _9_11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入a,b,c,d,e,f的值: ");
        /*
        ax+by = e   x = (e*d-b*f)/(a*d-b*c)
        cx+dy = f   y = (a*f-e*c)/(a*d-b*c)
         */
        /*
        a1: 0.0, b1: 1.0, c1: -1.0
        a2: 0.0, b2: 1.0, c2: 0.0
         */
        double a = sc.nextDouble(), b = sc.nextDouble(), c = sc.nextDouble(), d = sc.nextDouble(), e = sc.nextDouble(), f = sc.nextDouble();
        LinearEquation equation = new LinearEquation(a, b, c, d, e, f);
        if (!equation.isSolvable()) {
            System.out.println("The equation has no solution.");
        } else {
            System.out.println("x=" + equation.getX() + " y=" + equation.getY());
        }
    }
}

@Getter
@AllArgsConstructor
class LinearEquation {
    /*
    ax+by = e
    cx+dy = f
     */
    private double a, b, c, d, e, f;

    public double getX() {
        return (e * d - b * f) / (a * d - b * c);
    }

    public double getY() {
        return (a * f - e * c) / (a * d - b * c);
    }

    public boolean isSolvable() {
        return Math.abs(a * d - b * c) > 1e-6;
    }
}

