package 基础.面向对象;

public sealed class 密封类 permits 子类1  {
    // sealed修饰的类为密封类, 必须显示指明子类

}

final class 子类1 extends 密封类{
    // 密封类的子类必须有final修饰, 该子类无法再被继承
}
