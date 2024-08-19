package 课程.实验课.课堂练习;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入三角形的三条边长: ");
        double side1 = sc.nextDouble(), side2 = sc.nextDouble(), side3 = sc.nextDouble();
        Triangle triangle = new Triangle(side1, side2, side3);

    }
}

class IllegalTriangleException extends Exception {// 继承Exception类


    public IllegalTriangleException(String message) {
        super(message);
    }
}

class Triangle {
    double side1, side2, side3;

    public Triangle(double side1, double side2, double side3) throws IllegalTriangleException {
        if (side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        } else {
            throw new IllegalTriangleException("输入的边长无法构成三角形");
        }
    }
}
