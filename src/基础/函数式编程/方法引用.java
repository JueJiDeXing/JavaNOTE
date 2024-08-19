package 基础.函数式编程;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class 方法引用 {
    public static void main(String[] args) {
        Integer[]arr={3,1,6,4,9,3,6,4,8,2,5,8,3};
        Arrays.sort(arr, 方法引用::method);// ::方法引用符,被引用方法的形参和返回值要与抽象方法一致
        System.out.println(Arrays.toString(arr));

        //-------------------
        //引用静态方法
        //类名::静态方法     例 Integer::parseInt
        ArrayList<String>list=new ArrayList<>();
        Collections.addAll(list,"1","2","3");
        list.stream().map(Integer::parseInt).forEach(System.out::println);

        //引用成员方法
        // 对象::成员方法
        // 1.其他类 其他类对象::方法名
        // 2.本类 this::方法名   //引用处不能是静态方法
        // 3.父类 super::方法名  //引用处不能是静态方法
        list.stream().filter(new Student2()::method1);

        //引用构造方法
        //类名::new   例 Student::new
        ArrayList<String>list1=new ArrayList<>();
        Collections.addAll(list1,"zhangSan,18","liSi,19");
        /*
        List<Student> collect = list1.stream().map(new Function<String, Student>() {
            @Override
            public Student apply(String s) {
                String[] arr = s.split(",");
                return new Student(arr[0], Integer.parseInt(arr[1]));
            }
        }).collect(Collectors.toList());
         */
        List<Student2> collect = list.stream().map(Student2::new).collect(Collectors.toList());;

        // 类名::成员方法
        // 例String::toUpperCase    String toUpperCase(void)
        // 特有规则:被引用方法的形参需要与抽象方法的第二个到最后一个保持一致

        //抽象方法的第一个参数:表示被引用方法的调用者,决定了可以引用哪些类中的方法
        // 在Stream流中,第一个参数通常表示流的每一个数据,假设是字符串,那么方法引用时只能引用String类的方法
        // 如果没有第二个参数,说明被引用的方法是无参的成员方法

        //引用数组的构造方法--
        //数据类型[]::new
        ArrayList<Integer>list3=new ArrayList<>();
        Collections.addAll(list3,1,2,3);
        Integer[] array = list3.stream().toArray(Integer[]::new);//数组类型与流中的数据类型要一致

    }
    public static int method(int n1,int n2){
        return  n2-n1;
    }
}
class Student2 {
    String name;
    int age;

    public Student2() {
    }
    public Student2(String str) {
        String[] arr = str.split(",");
        this.name=arr[0];
        this.age=Integer.parseInt(arr[1]);
    }
    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean method1(String s){
        return true;
    }
}
