package 基础.集合.单列集合Collection.List.ArrayList;

import java.util.ArrayList;
//java.util.ArrayList<E>泛型
//集合,长度可变,自动扩容
//动态数组(笔记文件Note->array())
public class noteArrayList {
    public static void main(String[] args) {
        // 只能存储引用数据类型,存储的是地址,所以不能给一个对象重复赋值添加
        // 如果要存储基本数据类型,需要转换为包装类

        //1 构造方法
        //<引用数据类型>
        ArrayList<String> List1 = new ArrayList<>();//后一个类型可以省略

        //2 成员方法 增删改查
        // boolean .add([int index,] E element)//(指定位置)添加元素,返回boolean是否添加成功
        List1.add(0,"a");
        List1.add(0,"bc");//相当于insert
        List1.add("d");//相当于append
        System.out.println(List1);//[bc,a,d]

        // E .remove(int index) //删除指定位置元素,返回该指定元素
        // boolean .remove(E e) //删除指定元素,返回boolean是否删除成功
        String str1= List1.remove(0);
        List1.remove("a");
        System.out.println(List1+str1);//[d]bc

        // E .set(int index,E e)修改指定位置的元素,并返回该元素
        String str2 = List1.set(0, "p");
        System.out.println(List1+str2);//[p]d

        // E .get(int index)获取指定位置元素
        String str3=List1.get(0);
        System.out.println(str3);//p

        // int .indexOf(E e)获取指定元素的索引
        // int .lastIndexOf(E e)倒序查找
        System.out.println(List1.indexOf("p"));//0

        // int size()返回集合长度
        System.out.println(List1.size());//1

        // .clear()清空集合内容
        List1.clear();
        System.out.println(List1);//[]

        //3 遍历集合
        for (int i = 0; i < List1.size(); i++) {
            String s=List1.get(i);
            System.out.println(s);
        }
        for (String s : List1) {
            System.out.println(s);
        }
        //注意长度可变
        ArrayList<String>list=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {//要添加几个数据,写准确长度
            list.add("a");
        }

        //4 包装类
        /*byte->Byte short->Short
        * /---- char->Character  int->Integer ----/
        * long->Long float->Float
        * double->Double boolean->Boolean
        * */
        ArrayList<Integer> List2=new ArrayList<>();
        List2.add(1);
        List2.add(2);
        List2.add(3);
        List2.add(4);
        System.out.println(List2);//[1,2,3,4]

    }
}
