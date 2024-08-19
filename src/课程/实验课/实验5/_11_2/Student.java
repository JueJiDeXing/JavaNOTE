package 课程.实验课.实验5._11_2;

/**
 学生
 */
public class Student extends Person {
    public static final String ONE = "大一", TWO = "大二", THREE = "大三", FOUR = "大四";
    String classStatus;

    public Student(String name, String address, String phoneNum, String emil, String classStatus) {
        super(name, address, phoneNum, emil);
        this.classStatus = classStatus;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" + "classStatus=" + classStatus + ", name='" + name + '\'' + ", address='" + address + '\'' + ", phoneNum='" + phoneNum + '\'' + ", emil='" + emil + '\'' + '}';
    }
}
