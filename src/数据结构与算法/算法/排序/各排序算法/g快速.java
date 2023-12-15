package 数据结构与算法.算法.排序.各排序算法;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class g快速 {

    //每轮找一个基准点,小的放左边,大的右边
    public void quickSort(int[] a, int partitionMethod) {
        quick(a, 0, a.length - 1, partitionMethod);
    }

    private void quick(int[] a, int left, int right, int partitionMethod) {
        //递归主函数
        if (left >= right) {
            return;
        }
        //快排核心方法:分区
        // 找基准点,小的放左边,大的放右边
        int p = switch (partitionMethod) {
            case 1 -> partition1(a, left, right);
            case 2 -> partition2(a, left, right);
            case 3 -> partition3(a, left, right);
            case 4 -> partition4(a, left, right);
            default -> throw new RuntimeException("partitionMethod:" + partitionMethod + "错误");
        };
        System.out.println("分区:" + Arrays.toString(a));

        quick(a, left, p - 1, partitionMethod);//对两个区域再次分区排序
        quick(a, p + 1, right, partitionMethod);
    }

    /**
     <h1>单边快排</h1>
     选择最右元素作为基准点<br>
     <br>
     j指针负责找到小的元素,一旦找到则与i交换(交换数组元素值)<br>
     i指针负责找到大的元素<br>
     最后基准点与i交换,i即为分区位置<br>
     */
    /*示例:
    4 3 7 2 9 8 1 5  i=0 j=0 left=0 right=7  (j找到小的,ij相等不交换)(i++,j++)
    4 3 7 2 9 8 1 5  i=1 j=1 left=0 right=7  (j找到小的,ij相等不交换)(i++,j++)
    4 3 7 2 9 8 1 5  i=2 j=2 left=0 right=7  (j找到小的,ij相等不交换)(i找到大的,i停止,j++)
    4 3 7 2 9 8 1 5  i=2 j=3 left=0 right=7  (j找到小的)(此时i找到大的,j找到小的,ij交换)
    4 3 2 7 9 8 1 5  i=2 j=3 left=0 right=7  (交换后,i++,j++)
    4 3 2 7 9 8 1 5  i=3 j=4 left=0 right=7  (i找到大的,i停止)
    4 3 2 7 9 8 1 5  i=3 j=5 left=0 right=7
    4 3 2 7 9 8 1 5  i=3 j=6 left=0 right=7  (j找到小的)(此时i找到大的,j找到小的,ij交换)
    4 3 2 1 9 8 7 5  i=3 j=6 left=0 right=7
    4 3 2 1 9 8 7 5  i=4 j=7 left=0 right=7  (j到头)(j到头,i与right交换)
    4 3 2 1 5 8 7 9  i=4 j=7 left=0 right=7
     */
    private int partition1(int[] a, int left, int right) {
        int pv = a[right];//基准点元素值
        int i = left;
        int j = left;
        while (j < right) {
            if (a[j] < pv) {//找到小的
                if (i != j) {
                    swap(a, i, j);
                    i++;
                }
            }
            if (a[right] >= a[i]) {//i没有找到大的则自增,否则停止等待j找到小的元素并互换
                i++;
            }
            j++;
            System.out.println(Arrays.toString(a) + " i=" + i + " j=" + j);
        }
        swap(a, i, right);//最后交换
        return i;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     <h1>双边快排</h1>
     选择最左元素作为基准点<br>
     <br>
     j指针负责找到小的元素,一旦找到则与i交换(交换数组元素值)<br>
     i指针负责找到大的元素,一旦找到则与j交换(交换数组元素值)<br>
     i从左向右,j从右向左<br>
     最后基准点与i交换,i即为分区位置
     */
    private int partition2(int[] a, int left, int right) {
        int pv = a[left];//基准点元素值
        int i = left;
        int j = right;
        while (i < j) {
            //必须先找小的再找大的(先j后i),否则最后与基准点互换时会把大的换到左边(因为此时i,j相遇,而i找的是大的)
            while (i < j && a[j] > pv) {
                //寻找小的
                j--;
            }
            while (i < j && pv >= a[i]) {
                //寻找大的
                i++;
            }
            //找到则交换
            swap(a, i, j);
        }
        swap(a, left, i);
        return i;
    }

    /**
     <h1>快速排序:优化</h1>
     优化1:随机基准点<br>
     */
    private int partition3(int[] a, int left, int right) {
        int index = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, index, left);
        int pv = a[left];//基准点元素值
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && a[j] > pv) {
                //寻找小的
                j--;
            }
            while (i < j && pv >= a[i]) {//内层循环也要判断i<j
                //寻找大的
                i++;
            }
            //找到则交换
            swap(a, i, j);
        }
        swap(a, left, i);
        return i;
    }

    /**
     <h1>快速排序:优化</h1>
     优化2:重复元素处理<br>
     循环内,i从left+1(left为基准点)开始,从左到右找大于等于的,j从right开始,从右到左找小于等于的<br>
     ij找到后交换,i++,j-- <br>
     循环外,j和基准点交换,j即为分区位置
     <br>
     */
    private int partition4(int[] a, int left, int right) {
        int pv = a[left];
        int i = left + 1;
        int j = right;
        //while循环是小于等于
        // 因为i是从left+1开始的,可以想象在最后left+1=right时,i=j=right
        // while循环不进入,直接交换了left与right
        // 而使用<=会进入循环,ij的位置一定是正确的,退出循环后j的位置从right减到left,left与j交换不会破坏结果的正确性
        while (i <= j) {
            while (i <= j && a[i] < pv) {
                i++; //寻找大的
            }
            while (i <= j && a[j] > pv) {
                j--; //寻找小的
            }
            //上面两个循环确保ij都在正确的位置
            if (i <= j) {//如果i>j则不交换,保证不会交换错
                swap(a, i, j);
                i++;
                j--;
            }
        }
        swap(a, left, j);//交换与返回是j不是i
        return j;
    }
}
