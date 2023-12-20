package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.接口隔离原则.after;

/**

 */
public class MySafetyDoor1 implements AntiTheft,Fireproof,Waterproof {
    public void antiTheft() {
        System.out.println("防盗");
    }

    public void fireproof() {
        System.out.println("防火");
    }

    public void waterproof() {
        System.out.println("防水");
    }
}
