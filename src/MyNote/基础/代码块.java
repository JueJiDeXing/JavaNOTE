package MyNote.基础;

public class 代码块 {
    public int age;
    {//局部代码块,用于释放内存,限制a的作用域
        int a=5;
    }

    {//构造代码块,优先构造方法执行,可以把构造方法中重复的内容抽取写入构造代码块
        System.out.println("开始创建对象了");
        //不灵活,淘汰
        //推荐方法:写在有参构造中,无参构造里使用this(0);
    }
    static {//静态代码块,随着类的加载而加载,自动触发只执行一次,可用于初始化,防止内存溢出
        System.out.println("静态代码块");
        //例:在学生信息管理系统里,先加入一个账户,后面再进行增删改查
    }
    public 代码块() {
    }
    public 代码块(int age) {
        System.out.println("构造方法");
        this.age = age;
    }
}
