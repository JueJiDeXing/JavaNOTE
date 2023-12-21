package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.iterator迭代器;


public interface StudentIterator {

    //判断是否还有元素
    boolean hasNext();

    //获取下一个元素
    Student next();
}
