package 课程.实验课.实验5._11_2;

/**
 人
 */
public class Person {
    String name;
    String address;
    String phoneNum;
    String emil;

    public Person() {
    }

    public Person(String name, String address, String phoneNum, String emil) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.emil = emil;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", emil='" + emil + '\'' +
                '}';
    }
}
