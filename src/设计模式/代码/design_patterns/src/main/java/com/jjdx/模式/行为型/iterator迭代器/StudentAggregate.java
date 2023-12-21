package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.iterator迭代器;


public interface StudentAggregate {

    //添加学生功能
    void addStudent(Student stu);

    //删除学生功能
    void removeStudent(Student stu);

    //获取迭代器对象功能
    StudentIterator getStudentIterator();
}
