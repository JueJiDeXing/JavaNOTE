package 集合;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class noteStream {
    //stream流
    public static void main(String[] args) {
        //例
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "a1", "a22", "a634", "b12", "c14");

        Stream<String> list2 = list1.stream().filter(str -> str.startsWith("a"));
        Stream<String> list3 = list2.filter(str -> str.length() <= 3);//过滤
        list3.forEach(System.out::println);//输出a1 a22

        //获取stream流(流水线)
        //单列集合default Stream<E> stream()
        //双列集合无stream流,需要先转成Set集合(KeySet,EntrySet)
        //数组public static<T>Stream<T>stream(T[]array)
        int[] arr = {18, 35, 4, 51, 72, 32, 20};
        IntStream intStream = Arrays.stream(arr);
        //零散数据public static<T>Stream<T>of(T...value)如果传递数组必须是引用数据类型
        Stream.of(4, 2, 8, 7, 5, 4, 5).filter(num -> num < 5).forEach(System.out::println);

        //中间方法----------------------
        //中间方法返回新的流,原来的流只能用一次,用完就关闭,建议使用链式编程
        //修改流的数据不会影响原集合/动态数组
        //Stream<T>filter(Predicate<? super T>predicate)过滤元素
        //Stream<T>limit(long maxSize)获取前几个元素
        //Stream<T>skip(long n)跳过前几个元素
        //Stream<T>distinct()去重(依赖hashCode和equals方法)
        //static<T>Stream<T>concat(Stream a,Stream b)合并两个流
        //Stream<R>map(Function<T,R> mapper)转换流的数据类型

        ArrayList<String> list_1 = new ArrayList<>();
        Collections.addAll(list_1, "a-1", "b-2", "c-3");
        Stream<Integer> new_list = list_1.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s.split("-")[1]);
                //字符串转整数
            }
        });
        //终结方法---------------
        //void forEach(Consumer action)遍历
        //long count()统计个数
        //E[] toArray(new IntFunction<E[]>)收集放入数组
        //collect(Collector collector)收集放入集合
        ArrayList<String> list_2 = new ArrayList<>();
        Collections.addAll(list_2, "a-1", "b-2", "c-3");
        String[] list_3 = list_2.stream().toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {//value:数据的个数,需要与数组长度一致
                return new String[value];
            }
        });
        //toArray:创建一个指定类型的数组,底层依次得到流里的每一个数据放入数组

        ArrayList<String> list_4 = new ArrayList<>();
        Collections.addAll(list_4, "a-z-1", "a-x-2", "a-y-3", "b-1-w");

        Map<String, Integer> map = list_4.stream()
                .filter(s -> "a".equals(s.split("-")[0]))
                //Function(流的类型,值的类型)
                //Function(流的类型,键的类型)
                .collect(Collectors.toMap(//s:流里的每一个数据
                        s ->s.split("-")[1],//参1返回键
                        s -> Integer.parseInt(s.split("-")[2])//参2返回值
                ));
        System.out.println(map);//{x=2, y=3, z=1}
    }
}
