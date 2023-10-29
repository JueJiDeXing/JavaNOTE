package MyNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class 泛型 {
    //泛型类<>
    public static void main(String[] args) {
        //未指定集合元素的类型时默认为Object
        ArrayList<Object> list=new ArrayList<>();
        list.add(111);
        list.add("aaa");
        Iterator<Object> it=list.listIterator();
        while (it.hasNext()){
            //缺点:不能使用子类特有方法,如字符的.length()
            Object obj=it.next();
            System.out.println(obj);
        }
        //泛型--------------------------
        ArrayList<String> list2=new ArrayList<>();
        //<>给数据类型作约束
        //伪泛型,仅在编译阶段有效,编译后变为字节码文件.class后泛型约束会被"擦除"
        //泛型不能写基础数据类型(因为存储时会转为Object类型)
        //传递数据时可传入子类对象
    }
    //泛型类写法
    //修饰符 class 类名 <类型>{}
    class Ye{}
    class Fu{}
    class Zi{}

    private static class MyArrayList<E> {//类型字母可随便写T,E,K,V四个字母比较常用
        Object[]obj=new Object[10];
        int size;

        public boolean add(E e){
            obj[size]=e;
            size++;
            return true;
        }

        public E get(int index){
            return (E)obj[index];
        }

        @Override
        public String toString() {
            return Arrays.toString(obj);
        }
        //泛型方法
        //修饰符<类型>返回值类型 方法名(类型 变量名){}
        //当一个类只有一个方法是不确定类型时,泛型直接写在方法上
        public <T>void method(T t){
            //当调用方法时才会确定T的类型
        }

        //泛型不具备继承性,但数据具备继承性
        //例:
        public static void method2(ArrayList<Ye>list){
            ArrayList<Ye>list1=new ArrayList<>();
            ArrayList<Fu>list2=new ArrayList<>();
            ArrayList<Zi>list3=new ArrayList<>();
            //测试类里:
            //method2(list1);
            //method2(list2);//报错
            //method2(list3);//报错
            //泛型里面写的是什么类型,那么只能传递什么类型的数据
            //但是数据具备继承性,list1里可添加这三种类型的数据
        }

        //泛型的通配符
        public void method3(ArrayList<? extends Ye>list){
            // ?为限定符
            // ? extends E:表示可接受的参数类型为E或E所有子类
            // ? super E:E或E所有的父类
        }
    }
    //可变参数
    public void meth(String...strings){
        for (String str:strings){
            System.out.println(str);
        }
    }
    //泛型接口
    //修饰符 interface 接口名<类型>{}
    public interface inter<E>{
        //1.实现类有明确类型时,实现时给出具体类型,例:implement inter<String>
        //2.实现类延续泛型,创建实现类对象时再确定类型,例:implement inter<E>
    }


}
