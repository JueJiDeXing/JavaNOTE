package demo.Class.Extends.EG;

public class 多态_抽象_接口_内部类 {
    //多态-----------------------------------------------------------------
    //场景:不同的角色进行注册,向函数传参
    public static void main(String[] args) {
        Person s = new Student();//
        Teacher t = new Teacher();
        register(s);
        register(t);

        //
        System.out.println(s.name);//person
        //调用成员变量时看左边的类型,如果父类中没有name,而子类中有也会报错

        //调用成员方法时 编译看左边类型.运行看右边的类型,如果子类没有该方法,使用父类的方法
        System.out.println(s.getName());// student
        System.out.println(t.getName());// teacher

        //多态缺点,无法调用子类的特有方法,因为编译时需要父类中有该方法,(运行时执行 子类方法)
        //解决方法:将类型变回子类类型
        Student s_ = (Student) s;
        s_.study();//学习

        //instanceof判断类


    }

    public static void register(Person p) {//既能接受老师,也能接受学生
        //参数能接收其子类对象
        System.out.println(p.getName());
    }
}
//抽象----------------------------------------------------------------
abstract class Person {//抽象类
    // 抽象类不能实例化,但是可以有构造方法,在子类实例化时调用
    // 抽象类的子类要么重写所有抽象方法,要么是抽象类,父类不一定是抽象类
    String name = "person";

    public abstract void work();
    // 抽象方法,在代码主体内容需要由子类决定时使用,要将类声明为抽象类
    // 抽象方法作用,防止子类的该方法混乱,比如:方法名,参数和是否有返回值不统一.抽象类强制按同一种格式重写


    public String getName() {
        return name;
    }
}

//-----------------------------------------------------------------
//接口,和抽象类相似,是一种规则,对行为的抽象
//场景:一个类中的一部分子类具有同种功能,而其他子类没有该功能
interface jieKoJiCheng{

}
interface manage extends jieKoJiCheng{
    //接口可以继承接口,允许多继承
    // 子接口的实现类需要重写所有父类的抽象方法

    //public interface 接口名{}
    //接口没有构造方法,不能实例化
    //接口与类是实现关系
    // public class 类名 implements 接口名1,接口名2{}支持多接口,弥补单继承的缺点
    //接口的子类(实现类)要么重写接口中的所有抽象方法,要么是抽象类
    int a = 10;//接口成员变量只能是常量,默认修饰public static final

    void method();//成员方法只能是抽象方法(<JDK7),默认修饰public abstract
    //多接口的方法有重名时,在类中只需要实现一个即可

    //JDK8 接口中可以定义有方法体的方法(默认,静态),需要用default修饰
    //默认方法不是抽象方法,所以不强制被重写,如果被重写,重写时不加default关键字
    //public可以省略,default不能省略,如果实现多个接口,接口存在相同名字的默认方法,就必须重写
    default void method2(){
        System.out.println("默认方法");
        method3();//调用私有方法
    }
    //静态方法不能重写,只能通过接口名调用,不能通过实现类名或对象名调用

    //JDK9 接口中可以定义私有方法
    // 普通私有方法,给default默认方法服务
    // 静态私有方法,为静态方法服务
    private void method3(){
        System.out.println("私有方法");
    }

    //适配器xxxxAdapter,接口名Adapter
    //创建一个类,空实现接口,然后新建一个类,继承实现类,重写其中需要的部分方法
    //为避免其他类创建适配器对象,适配器类需要用abstract修饰
}

class Student extends Person {
    String name = "student";

    @Override
    public void work() {//抽象方法的继承必须重写
        System.out.println("学习");
    }

    @Override
    public String getName() {
        return name;
    }

    public void study() {
        System.out.println("学习");
    }
}

class Teacher extends Person implements manage {
    String name = "teacher";


    @Override
    public void work() {
        System.out.println("教书");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void method() {

    }
    @Override
    public void method2(){
        System.out.println("重写默认方法");
    }
}

class HeadMaster extends Person implements manage {

    @Override
    public void work() {

    }

    @Override
    public void method() {

    }
}
//内部类
//B类是A类的一部分,而且B类单独存在没有意义
class Car{
    //内部类可以直接访问外部类的成员,包括私有
    //外部类要访问内部类,必须创建对象
    String carName;
    int carAge;
    int a;
    class Engine {//成员内部类,属于外部类的成员,和成员方法具有相同地位------
        String engineName;
        int engineAge;
        int a;
        //Car.this.a->外部的a  this.a->内部的a
    }
    //获取成员内部类对象
    //1. 在外部类中编写方法,对外提供内部类对象
    //例:public Engine getEngine(){return new Engine();}
    //2. 直接创建:  外部类名.内部类名 对象名=外部类对象.内部类对象
    //例: Car.Engine en=new Car().new Engine();

    static class Engine2{//静态内部类,特殊的成员内部类---------
        //静态只能访问静态
        //创建静态内部类对象:外部类名.内部类名 对象名=new 外部类名.内部类名();
        // 不需要创建外部类对象即可调用
        //调用内部类非静态方法:先创建对象,再用对象调用
        //调用内部类静态方法:外部类名.内部类名.方法名();
    }
    public void method(){//外部类方法
        class Inner{//局部内部类,与局部变量地位相同-----------
            //定义在内部类方法里,外界无法直接使用,需要在方法内部创建对象使用
            //可以访问外部类成员,也可以访问方法内的局部变量
        }
    }
}

//匿名内部类-----------------------
/*格式:

//接口
public interface 接口{
    public abstract void method();
}

//main函数中
new 接口(){   //new关键字空参构造创建匿名内部类对象
    //匿名内部类为下面部分,与接口具有实现关系
   @Override
   public void method(){
        重写方法
   }
}  //这个整体是一个对象,可以调用方法
new 类名(){重写抽象方法}   继承关系

/////使用
在测试类中有一个函数,function(Animal a){a.eat();}
以前的做法:新建一个Dog类继承Animal,然后创建Dog对象传入函数,function(d)
现在:function(
    new Animal(){
        @Override
        public void eat(){
            sout("狗吃骨头)
        }
    }
)


*/