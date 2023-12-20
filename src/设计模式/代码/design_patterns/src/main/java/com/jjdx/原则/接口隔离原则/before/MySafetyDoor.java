package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.接口隔离原则.before;

/**
安全门类
 */
public class MySafetyDoor implements SafetyDoor {
    public void antiTheft() {
        System.out.println("防盗");
    }

    public void fireProof() {
        System.out.println("防火");
    }

    public void waterProof() {
        System.out.println("防水");
    }
}
