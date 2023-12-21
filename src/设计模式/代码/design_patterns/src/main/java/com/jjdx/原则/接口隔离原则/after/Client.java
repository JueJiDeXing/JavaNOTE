package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.接口隔离原则.after;

/**
 <h1>接口隔离原则</h1>
 客户端不应该被迫依赖于它不使用的方法；一个类对另一个类的依赖应该建立在最小的接口上。
 */
public class Client {
    public static void main(String[] args) {
        //创建黑马安全门对象
        MySafetyDoor1 door1 = new MySafetyDoor1();//全防
        //调用功能
        door1.antiTheft();
        door1.fireproof();
        door1.waterproof();

        System.out.println("============");
        //创建传智安全门对象
        MySafetyDoor2 door2 = new MySafetyDoor2();//不防水
        //调用功能
        door2.antiTheft();
        door2.fireproof();
    }
}
