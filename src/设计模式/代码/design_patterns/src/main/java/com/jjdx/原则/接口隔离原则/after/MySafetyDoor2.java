package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.接口隔离原则.after;

/**
 安全门<br>
 不防水

 */
public class MySafetyDoor2 implements AntiTheft, Fireproof {
    public void antiTheft() {
        System.out.println("防盗");
    }

    public void fireproof() {
        System.out.println("防火");
    }
}
