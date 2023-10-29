package J_API;

import java.util.Arrays;

public class J_System {
    //系统
    public static void main(String[] args) {
        //void .exit(int status)终止虚拟机
        //----System.exit(0);   0为正常停止,非0为异常停止

        //long currentTimeMillis()返回当前系统的时间
        long time=System.currentTimeMillis();
        System.out.println(time);//从时间原点开始到现在的时间(单位ms)
        /*时间原点:C语言的生日1970年1月1日0点,中国时区有时差,时间原点为8点*/

        //void .arraycopy(Object src, int srcStartIndex,
        // Object dest, int destStartIndex, int length);拷贝数组
        // (数据源,开始索引,目的地,开始索引,拷贝个数)注意不要超出索引范围
        int[]arr1={1,2,3,4,5,6,7,8,9,10};
        int[]arr2=new int[10];
        System.arraycopy(arr1,0,arr2,2,5);
        System.out.println(Arrays.toString(arr2));//[0, 0, 1, 2, 3, 4, 5, 0, 0, 0]
        //对于引用数据类型,子类数组可以拷贝到父类数组
    }
}
