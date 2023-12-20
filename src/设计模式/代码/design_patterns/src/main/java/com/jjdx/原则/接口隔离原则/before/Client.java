package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.接口隔离原则.before;

/**
 一个门直接实现了三个接口,但是只需要其中一部分,应当将接口拆分
 */
public class Client {
    public static void main(String[] args) {
        MySafetyDoor door = new MySafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterProof();
    }
}
