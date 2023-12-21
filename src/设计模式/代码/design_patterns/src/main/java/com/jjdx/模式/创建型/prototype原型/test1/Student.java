package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.prototype原型.test1;

import java.io.Serializable;


public class Student implements Serializable {

    //学生的姓名
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
