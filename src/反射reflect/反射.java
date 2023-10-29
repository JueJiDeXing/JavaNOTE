package 反射reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class 反射 {
    //反射运行对封装类的成员变量,方法,构造函数的信息进行编程访问
    //例:IDEA利用反射获取方法的形参并显示在界面上

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //获取class对象的三种方式---------------------------------------------
        //Class.forName("全类名")  源代码阶段使用  .java和.class文件
        //类名.class  加载阶段使用   类加载到内存
        //对象.getClass()运行阶段使用 在内存中创建对象
        Class class1 = Class.forName("反射reflect.Student");//最常用
        System.out.println(class1);//包名＋类名

        Class class2 = Student.class;//一般作为参数传入synchronized()线程锁
        System.out.println(class2);

        Student student=new Student();
        Class class3=student.getClass();
        System.out.println(class3);

        System.out.println((class3==class2)+","+(class2==class1));//都为true

        //利用反射获取构造方法----------------------------------------------
        //Constructor<?>[] getConstructors()返回所有公共构造方法对象的数组
        //Constructor<?>[] getDeclaredConstructors()返回所有构造方法对象的数组
        //Constructor<T>[] getConstructors(Class<?>...parameterTypes)返回单个公共构造方法对象
        //Constructor<T>[] getDeclaredConstructors()返回单个构造方法对象
        Constructor[]constructors1= class1.getConstructors();
        for (Constructor con:constructors1){
            System.out.println(con);//只能获取public类型
            //public 反射reflect.Student(java.lang.String,int)
            //public 反射reflect.Student()
        }
        System.out.println("------------------------");
        Constructor[]constructors2= class1.getDeclaredConstructors();
        for (Constructor con:constructors2){
            System.out.println(con);//获取顺序随机
            //protected 反射reflect.Student(int)
            //public 反射reflect.Student(java.lang.String,int)
            //private 反射reflect.Student(java.lang.String)
            //public 反射reflect.Student()
        }
        System.out.println("------------------------");
        //获取单个指定类型的公共构造方法,不传参则获取无参构造
        Constructor constructors3= class1.getConstructor(String.class,int.class);
        System.out.println(constructors3);//public 反射reflect.Student(java.lang.String,int)
        //获取单个指定参数类型的构造方法
        Constructor constructors4= class1.getDeclaredConstructor(String.class);
        System.out.println(constructors4);//private 反射reflect.Student(java.lang.String)

        //获取构造方法的权限修饰符与参数等
        int modifiers = constructors4.getModifiers();//类的权限修饰符
        System.out.println(modifiers);// 2
        // 1表示public,2表示private,4表示protected,8表示static,16表示final,32表示synchronized,64表示volatile
        // 128表示transient,256表示native,512表示interface,1024表示abstract,2048表示strict
        Parameter[] parameters = constructors4.getParameters();//获取全部参数
        System.out.println(parameters);

        //利用反射创建对象
        //T newInstance(Object...initargs)根据指定方法创造Constructor对象
        //setAccessible(boolean flag)是否取消访问检查
        //反射只能获取构造方法信息.没有访问权限,仍然不能调用私有构造方法,需要取消权限后才能调用
        constructors4.setAccessible(true);//暴力反射,临时取消权限校验
        Student student1 =(Student) constructors4.newInstance("zhanSan");
        System.out.println("#######################");
        //利用反射获取成员变量-------------------------------------------
        //Field[] getFields()返回所有公成员变量对象的数组
        //Field[] getDeclaredFields()返回所有成员变量对象的数组
        //Field getFields(String name)返回单个公共成员变量对象
        //Field getDeclaredFields(String name)返回单个成员变量对象
        Field[] fields=class1.getFields();
        for (Field f:fields){
            System.out.println(f);//只返回带public权限的参数
            //public java.lang.String 反射reflect.Student.name
        }
        System.out.println("---------------------------");
        Field age=class1.getDeclaredField("age");
        System.out.println(age.getModifiers());//2
        System.out.println(age.getType());//int
        //void set(Object obj,Object value)赋值
        //Object get(Object obj)获取值
        Student student2=new Student("z",18);
        age.setAccessible(true);//取消私有权限
         int stuAge= (int) age.get(student2);//获取对象的age信息
        System.out.println(stuAge);
        age.set(student2,20);//修改对象的age

        //利用反射获取成员方法-------------------------------------------
        //Method[] getMethods()返回所有公共成员方法对象的数组  //会包含父类中所有公共的成员方法
        //Method[] getDeclaredMethods()返回所有成员方法对象的数组  //不会获取到父类
        //Method getMethods(String name,Class<?>...parameterTypes)返回单个公共成员方法对象,由于方法重载,还需要传入参数类型
        //Method getDeclaredMethods(String name,Class<?>...parameterTypes)返回单个成员方法对象

        //Object invoke(Object obj,Object...args)用该对象调用方法
    }
}
