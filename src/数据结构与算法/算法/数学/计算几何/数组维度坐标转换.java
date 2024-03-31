package 数据结构与算法.算法.数学.计算几何;

public class 数组维度坐标转换 {
    /**
     三维坐标转一维
     */
    static int convert(int x, int y, int z) {
        int B = 100, C = 100;//A*B*C的三维数组
        return (x * B + y) * C + z;
    }
}
