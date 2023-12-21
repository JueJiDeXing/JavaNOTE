package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.iterator迭代器;

import java.util.ArrayList;
import java.util.List;


public class StudentAggregateImpl implements StudentAggregate {

    private List<Student> list = new ArrayList<Student>();

    public void addStudent(Student stu) {
        list.add(stu);
    }

    public void removeStudent(Student stu) {
        list.remove(stu);
    }

    //获取迭代器对象
    public StudentIterator getStudentIterator() {
        return new StudentIteratorImpl(list);
    }
}
