package demo.Class.Extends.EG;//包名规则,公司域名反写.文件作用
//使用其他类时用  包名.类名 对象名= new 包名.类名
//简写导包  import 包名.类名    调用时直接用类名声明
//同一个包中的类不需要导包,java.lang不需要导包
//如果同时使用两个包中的同名类,需要使用全类名

public class Test {
    public static void main(String[] args) {
        Hasky h=new Hasky();
        h.eat();
        h.drink();

        h.lookHome();
        h.breakHome();

    }
}
