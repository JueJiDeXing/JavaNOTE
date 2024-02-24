package 基础.J_API;

import java.util.Arrays;
import java.util.Comparator;

public class J_Arrays {//操作数组的工具类
    public static void main(String[] args) {
        //String Arrays.toString(Array arr)
        int[]arr={1,2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(arr));

        //int Arrays.binarySearch(Array arr,number key)二分查找,返回索引值,未找到返回-插入点-1   (要求数组升序)
        System.out.println(Arrays.binarySearch(arr,10));//输出-9,key10应在数组的index=8处,-8-1=-9

        //Array Arrays.copyOf(Array arr,int length)指定长度拷贝数组,越界补0
        int[] arr2 = Arrays.copyOf(arr, 5);
        System.out.println(Arrays.toString(arr2));

        //Array Arrays.copyOf(Array arr,int startIndex ,int endIndex)指定索引范围(左闭右开)拷贝数组,越界补0
        int[]arr3=Arrays.copyOfRange(arr,1,7);
        System.out.println(Arrays.toString(arr3));

        //Arrays.fill(arr,val) 把数组的元素值都改为val
        Arrays.fill(arr,10);
        System.out.println(Arrays.toString(arr));

        // Arrays.sort(arr4,规则)排序
        Integer[]arr4={2,31,42,16,10,36,27,17};
        Arrays.sort(arr4,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("o1="+o1+",o2="+o2);
                return o1-o2;
            }
        });
        System.out.println(Arrays.toString(arr4));

        //compare(Integer o1, Integer o2)
        //o1:无序序列的元素(即遍历的某一个数组元素,开始时为第二个元素)
        //o2:有序序列的元素(已排序的元素,开始时为第一个元素,后面从已排序的部分倒着遍历与o1比较)
        //返回值:负数表示当前插入的数是小的,要放在前面  正数表示当前插入的数是大的,要放在后面  0表示相等,也放在后面


        //数组地址
        //每个int占4个字节,前一个元素+一个int字节=后一个元素的地址

        //对齐字节:所有的数据都是8个字节的整数倍,不足的用对齐字节补齐
        //动态数组=8字节markword+4字节class指针+4字节数组大小+n*int字节元素大小+对齐字节
        //例如一个数组有5个元素,则其大小=8+4+4+5*4+4(alignment)=40


    }
}
