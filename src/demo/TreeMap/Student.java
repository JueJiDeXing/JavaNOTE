package demo.TreeMap;

public class Student {
    String name;
    int age;
    String address;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name="+name+",age="+age+"}";
    }
}
