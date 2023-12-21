package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.memento备忘录.black_box;



public class RoleStateCaretaker {

    //声明RoleStateMemento类型的变量
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
