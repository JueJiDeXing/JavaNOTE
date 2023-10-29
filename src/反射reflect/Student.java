package 反射reflect;

public class Student {
    public String name;
    private int age;
    protected String AAA;
    final String BBB="";
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    protected Student( int age) {
        this.age = age;
    }
    private Student( String name) {
        this.age = age;
    }
    public Student() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
