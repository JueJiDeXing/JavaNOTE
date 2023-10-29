package demo.Class.Extends.EG;

public class Hasky extends Dog {
    public int abc;
    //子类不能继承构造方法,构造方法名应与类名相同
    //子类的所有构造方法默认先访问父类的无参构造再访问自己,因为子类初始化时可能使用到父类的数据
    public Hasky(){
        super(1);

        //super();写在第一行(不写会自动加),访问父类的无参构造方法
        //调用父类有参构造方法(需要父类有该构造方法)要手动写super(参数)调用
        //this(参数)在无参构造时给某一个成员变量赋予指定默认值,其他参数写系统默认值"null",0
    }
    public Hasky(int age ,int abc){
        super(age);
        this.abc=abc;
    }


    //子类能继承成员变量,但是不能直接调用私有成员变量,需要通过get()和set()file操作
    //子类不能继承私有方法
    public void breakHome() {
        System.out.println("哈士奇在拆家");
        this.eat();//子类只能访问父类非私有的成员
        this.age = 10;
        System.out.println(age);//通过就近原则寻找age,子类中如果也没有,就寻找父类的age
        System.out.println(this.age);
        System.out.println(super.age);//从父类开始寻找成员变量age
        this.breakHome2();

    }

    private void breakHome2() {
        System.out.println("私有方成员能在本类中访问");
    }

    @Override //重写父类方法注解,如果方法名和参数不同会提示报错
    public void lookHome() {//重写方法会进行覆盖继承
        System.out.println("看家?不存在的");
        // 重写时子类的访问权限要大于等于父类 (public > protected > none)
        // 返回值类型子类要小于等于父类
        // 重写尽量保持一致
        // 不能加到虚方法表的方法不能被重写
    }
    // 虚方法表:非private,非static,非final的方法,虚方法表的方法是累加继承的
    // 为防止多层继承时方法调用困难,在继承时,每个父类都会把一些方法记录在虚方法表上,然后交给子类,子类在基础上加上自己的虚方法,交给下一个子类
}
