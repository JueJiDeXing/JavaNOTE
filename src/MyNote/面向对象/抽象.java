package MyNote.面向对象;

abstract public class 抽象 {
    public void m1() {

    }

    private void m2() {
    }

    abstract void m3();

    //abstract private void m4();不允许abstract+private
    abstract protected void m4();
    //abstract final protected void m5();不允许abstract+final
}

//abstract final class C {}不允许abstract+final

