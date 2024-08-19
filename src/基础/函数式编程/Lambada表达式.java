package 基础.函数式编程;

import java.util.Arrays;
import java.util.Comparator;

public class Lambada表达式 {
    //Lambada表达式 (形参)->{方法体}
    // 参数类型可省略,如果只有一个参数可省略括号,如果只有一行方法体,可同时省略大括号,分号,return
    //只能简化函数式接口的匿名内部类的书写 (函数式接口@FunctionalInterface:只有一个抽象方法的接口)
    public static void main(String[] args) {
        // 示例1:数字按大小排序---------------------------------------
        Integer[] arr = {2, 31, 42, 16, 10, 36, 27, 17};
        Arrays.sort(arr, (Integer o1, Integer o2) -> {
            System.out.println("o1=" + o1 + ",o2=" + o2);
            return o1 - o2;
        });
        System.out.println(Arrays.toString(arr));

        //
        Integer[] arr1 = {2, 31, 42, 16, 10, 36, 27, 17};
        Arrays.sort(arr1, (o1, o2) -> {//参数类型可省
            System.out.println("o1=" + o1 + ",o2=" + o2);
            return o1 - o2;
        });
        System.out.println(Arrays.toString(arr1));

        //
        Integer[] arr2 = {2, 31, 42, 16, 10, 36, 27, 17};
        Arrays.sort(arr2, Comparator.comparingInt(o -> o));
        System.out.println(Arrays.toString(arr2));

        //
        Integer[] arr3 = {2, 31, 42, 16, 10, 36, 27, 17};
        Arrays.sort(arr3, Integer::compareTo);
        System.out.println(Arrays.toString(arr3));

        // 示例2:字符按长度排序----------------------------------
        String[] arr_1 = {"ab", "wiv", "c", "iveie", "amwe", "we"};
        Arrays.sort(arr_1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {//重写compare方法,根据长度排序
                return o1.length() - o2.length();
            }
        });
        System.out.println(Arrays.toString(arr_1));

        //
        String[] arr_2 = {"ab", "wiv", "c", "iveie", "amwe", "we"};
        Arrays.sort(arr_2, (o1, o2) -> o1.length() - o2.length());
        System.out.println(Arrays.toString(arr_2));

        //
        String[] arr_3 = {"ab", "wiv", "c", "iveie", "amwe", "we"};
        Arrays.sort(arr_3, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(arr_3));

    }
}
