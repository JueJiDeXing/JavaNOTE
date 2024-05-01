package 课程.实验课.实验4;

import lombok.Data;
import lombok.NoArgsConstructor;

public class _9_9 {
    public static void main(String[] args) {
        RegularPolygon r1 = new RegularPolygon();
        System.out.println("r1周长: " + r1.getPerimeter() + "\t  r1面积: " + r1.getArea());
        RegularPolygon r2 = new RegularPolygon(6, 4);
        System.out.println("r2周长: " + r2.getPerimeter() + "\t  r2面积: " + r2.getArea());
        RegularPolygon r3 = new RegularPolygon(10, 4, 5.6, 7.8);
        System.out.println("r3周长: " + r3.getPerimeter() + "\t  r3面积: " + r3.getArea());
    }

}

@Data
@NoArgsConstructor
class RegularPolygon {
    private int n = 3;
    private double side = 1;
    private double x = 0, y = 0;

    public RegularPolygon(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    public double getPerimeter() {
        return n * side;
    }

    public double getArea() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }

    public RegularPolygon(int n, double side) {
        this.n = n;
        this.side = side;
    }

}
