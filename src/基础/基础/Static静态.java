package 基础.基础;

public class Static静态 extends 类 {
    //extends继承
    // 子类可在父类的基础上添加其他功能,在两个类有共性时,提取重复内容组成父类
    // 不支持多继承,但支持多层继承一个类,只能有一个直接父类
    // 在没有继承时,默认继承object

    /*静态只能访问静态,非静态可以访问所有*/
    //main方法是静态的,所以其他方法也是静态的,因为静态只能访问静态
    String name;//默认属性public,默认值null
    private int age;//私有成员,只有在本类中才能访问
    public static String teacher;
    // public,对象能直接调用的属性       s1.teacher="王老师"
    // static,所有对象共用一个值,推荐用类名调用   基础.noteClass.teacher="王老师"
    // static属性随着类的加载而加载,优先于对象存在(静态存储区)
    // 对于工具类,其构造方法应为private,私有化防止创建工具类对象,其成员方法应为public static
    public void show1(Static静态 this) {
        //非静态方法可以访问所有成员变量
        age=1;
    }
    public static void show2(){
        //static方法没有this参数,不能访问成员变量,只能访问静态
        //static静态一般表示各对象共享
        teacher="aaa";//静态方法只能访问静态成员变量,因为非静态成员变量是随着对象的创建而产生的
        //static多用于测试类与工具类,在Javabean类中很少用
    }
    private void show3(){
    }
}
