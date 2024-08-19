package 课程.实验课.实验4;

public class _10_12 {
    public static void main(String[] args) {
        Triangle2D t1 = new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3), new MyPoint(5, 3.5));
        System.out.println("周长: " + t1.getPerimeter());
        System.out.println("面积: " + t1.getArea());
        System.out.println("(3,3)是否在三角形内: " + t1.contains(3, 3));
        Triangle2D t2 = new Triangle2D(new MyPoint(2.9, 2), new MyPoint(4, 1), new MyPoint(1, 3.4));
        System.out.println("t2是否在t1内: " + t1.contains(t2));
        Triangle2D t3 = new Triangle2D(new MyPoint(2, 5.5), new MyPoint(4, -3), new MyPoint(2, 6.5));
        System.out.println("t3是否与t1重叠: " + t1.overlaps(t3));
    }
}

class MyPoint {
    double x, y;

    public MyPoint() {
        x = 0;
        y = 0;
    }

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(MyPoint p) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }
}

class Triangle2D {
    MyPoint p1, p2, p3;

    public Triangle2D() {
        p1 = new MyPoint();
        p2 = new MyPoint(1, 1);
        p3 = new MyPoint(2, 5);
    }

    public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    /**
     面积
     S = AB × AC /2
     = (x2-x1,y2-y1) × (x3-x1,y3-y1) / 2
     = [(x2-x1)*(y3-y1) - (y2-y1)*(x3-x1)] / 2
     */
    public double getArea() {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x) / 2;
    }

    public static double getArea(MyPoint p1, MyPoint p2, MyPoint p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x) / 2;
    }

    /**
     周长
     */
    public double getPerimeter() {
        return p1.distance(p2) + p1.distance(p3) + p2.distance(p3);
    }

    /**
     点是否在三角形内
     如果点p在三角形ABC内,则 S(ABC) = S(ABp)+S(ACp)+S(BCp)
     */
    public boolean contains(MyPoint p) {
        double s1 = getArea();
        double s2 = getArea(p1, p2, p) + getArea(p1, p3, p) + getArea(p2, p3, p);
        return Math.abs(s1 - s2) < 1e-6;
    }

    public boolean contains(int x, int y) {
        return contains(new MyPoint(x, y));
    }

    /**
     给定三角形是否在本三角形内
     三个顶点都在三角形内即可
     */
    public boolean contains(Triangle2D t) {
        return contains(t.p1) && contains(t.p2) && contains(t.p3);
    }

    /**
     给定三角形是否与本三角形重叠
     */
    public boolean overlaps(Triangle2D t) {
        // 检查两个三角形的边是否有交点
        if (isIntersect(p1, p2, t.p1, t.p2) || isIntersect(p1, p2, t.p2, t.p3) || isIntersect(p1, p2, t.p3, t.p1)) {
            return true;
        }
        if (isIntersect(p2, p3, t.p1, t.p2) || isIntersect(p2, p3, t.p2, t.p3) || isIntersect(p2, p3, t.p3, t.p1)) {
            return true;
        }
        if (isIntersect(p3, p1, t.p1, t.p2) || isIntersect(p3, p1, t.p2, t.p3) || isIntersect(p3, p1, t.p3, t.p1)) {
            return true;
        }
        return false;
    }

    /**
     判断两条线段是否相交
     两条线段不相交, 当且仅当 存在其中一条线段的两个端点均在另一条线段的一侧
     */
    private boolean isIntersect(MyPoint L1_p1, MyPoint L1_p2, MyPoint L2_p1, MyPoint L2_p2) {
        double d1 = pos(L2_p1, L2_p2, L1_p1), d2 = pos(L2_p1, L2_p2, L1_p2);
        double d3 = pos(L1_p1, L1_p2, L2_p1), d4 = pos(L1_p1, L1_p2, L2_p2);
        return !((d1 * d2 >= 0)) || ((d3 * d4 >= 0));// 同号 -> 在同一侧
    }

    /**
     计算点在线段的方位(上或下)
     */
    private double pos(MyPoint p1, MyPoint p2, MyPoint p) {
        return (p2.x - p1.x) * (p.y - p1.y) - (p.x - p1.x) * (p2.y - p1.y);
    }


}
