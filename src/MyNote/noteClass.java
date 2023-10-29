package MyNote;

public class noteClass {//public修饰的类要与文件名相同,如果一个文件要写多个类(不建议),其他类不能加public
    //权限修饰符private<friendly<protected<public
    //本类,同一个包中的其他类,不同包下的子类,不同包下的无关类
    public static String teacher;//公共成员,所有类都能访问
    protected int mmm;//protected受保护的,本类与继承子类可见
    String name;//默认friendly,在同一个包中可见,不同包中的子类和非子类不可见
    private int age;//私有成员,只有在本类中才能访问

    final int fff=10;//final最终的,不可被改变的,final修饰的方法不能被重写,类不能被继承,变量只能赋值一次

    private static final String value = "null";//不可修改的常量

    public noteClass(String name, int age, int mmm) {
        this.name = name;//this指当前方法调用者的地址
        this.age=age;
        this.mmm=mmm;
    }

    //通过公共方法封装,调用方法设置和访问数据
    public void setAge(int a){//自动生成set和get和构造方法的快捷键:alt+fn+insert.快捷插件PTG
        if(18<=a&&a<=30){
            age=a;
        }else {
            System.out.println("非法数据");
        }

    }
    public int getAge(){
        return age;
    }



    //就近原则
    public void method(){
        int age=10;
        System.out.println(age);//就近原则,访问方法里的局部变量age
        System.out.println(this.age);//this关键字访问类属性的age
    }

    //构造方法,给成员变量初始化
    public noteClass(){
    }//空参构造方法
    // 如果没写构造方法,虚拟机会自动加一个空参构造方法
    public noteClass(int age){
        this.age=age;
    }//带参构造方法
    //内存分配
    /*
    栈:方法块先进后出,方法里的对象从栈指向堆
    堆:存储类对象的成员变量的值,以及成员方法的地址,指向方法区
    //方法区:(在栈中创建类的第一个对象时开辟内存)临时存储类的属性和方法
    元空间
    本地方法栈
    寄存器
    */

    //可变参数
    //函数名(其他参数,可变参数类型...可变参数数组名){}
}
