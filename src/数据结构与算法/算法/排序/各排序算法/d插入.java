package 数据结构与算法.算法.排序.各排序算法;

public class d插入 {
    //将数组分为两部分,已排序[0,low-1]和未排序[low,length-1]
    //每次从未排区取出low位置的元素,寻找插入位置

    public static void insertSort(int[] a) {
        //insertion2(a, 1);
        //改为非递归
        for (int low=1;low<a.length;low++){
            int t = a[low];
            int i = low - 1;
            while (0 <= i && t < a[i]) {//寻找插入位置
                a[i + 1] = a[i];//比待插入值大的右移,空出一个位置
                i--;
            }
            if (i != low - 1) {//找到插入位置
                a[i + 1] = t;
            }
        }
    }

    private void insertion(int[] a, int low) {
        if (low == a.length) {
            return;
        }
        int t = a[low];
        int i = low - 1;
        while (0 <= i && t < a[i]) {//寻找插入位置
            a[i + 1] = a[i];//比待插入值大的右移,空出一个位置
            i--;
        }
        if (i != low - 1) {//找到插入位置
            a[i + 1] = t;
        }
        insertion(a, low + 1);//递归插入下一个
    }
}
