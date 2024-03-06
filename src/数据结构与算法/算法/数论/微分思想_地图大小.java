package 数据结构与算法.算法.数论;

import 数据结构与算法.数据结构.树.树实现.线段树.线段树实现.SegmentTree1;

import java.util.Scanner;

public class 微分思想_地图大小 {
    /*
    输入整数n,表示有n块地图
    然后输入n行,每行四个浮点数x1,y1,x2,y2
    (x1,y1)为左上角,(x2,y2)为右下角,构成一个地图块
    求地图的总面积(n个地图块的并集),精确到小数点后两位
    0 <= x,y <= 10^5
     */

    /**
     TODO 地图在中间有缺块, y是分离的, 如何处理?
     要得到精确结果应当使用线段树{@link SegmentTree1}
     */
    public static void main(String[] args) {
        /*
        微分算法,对x进行等分,求每一小段的面积之和
         */
        //获取输入的地图块坐标
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] x1 = new double[n], y1 = new double[n];
        double[] x2 = new double[n], y2 = new double[n];
        double x_max = 0, x_min = Integer.MAX_VALUE;//获取输入的同时记录x的max和min
        for (int i = 0; i < n; i++) {
            x1[i] = sc.nextDouble();
            y1[i] = sc.nextDouble();
            x2[i] = sc.nextDouble();
            y2[i] = sc.nextDouble();
            x_max = Math.max(x_max, x2[i]);
            x_min = Math.min(x_min, x1[i]);
        }
        //寻找最大可等分精度
        double x_d = 0;//等分精度
        double total_x = x_max - x_min;//总长度
        double[] splits = new double[]{1, 0.5, 0.1, 0.05, 0.01, 0.005, 0.001, 0.0005, 0.0001};//如果有必要可以继续增加精度
        for (double split : splits) {
            if (total_x % split == 0) {//当前精度能等分
                x_d = split;
                break;
            }
        }
        //处理
        double sum = 0;
        //根据等分的x递进，获取该x偏右的上下y值，计算微小面积，再求和
        for (double x = x_min; x < x_max; x += x_d) {
            double[] y = getY(x + 0.001, n, x1, y1, x2, y2);//获取在这个x上的y的边界
            sum += (y[0] - y[1]) * x_d; //y[0]=y_max  y[1]=y_min  (max-min) * x的等分区间长 = 小块面积
        }
        //输出
        System.out.printf("%.2f", sum);
    }

    /**
     获取x=x0时y的max和min
     */
    private static double[] getY(double x0, int n, double[] x1, double[] y1, double[] x2, double[] y2) {
        double y_max = 0;
        double y_min = 9999999;
        for (int i = 0; i < n; i++) {//遍历所有地图块
            if (x1[i] <= x0 && x0 <= x2[i]) {//x0在这个地图块上
                y_max = Math.max(y_max, y2[i]);//更新y的max和min
                y_min = Math.min(y_min, y1[i]);
            }
        }
        return new double[]{y_max, y_min};
    }
}
