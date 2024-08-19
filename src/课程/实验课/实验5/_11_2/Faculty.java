package 课程.实验课.实验5._11_2;

/**
 教员
 */
public class Faculty extends Employee {

    int officeHours;//办公时长
    String level;

    public Faculty(String name, String address, String phoneNum, String emil, String officeRoom, int salary, MyDate employeeDate, int officeHours, String level) {
        super(name, address, phoneNum, emil, officeRoom, salary, employeeDate);
        this.officeHours = officeHours;
        this.level = level;
    }

    public Faculty() {
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "officeHours=" + officeHours +
                ", level=" + level +
                ", officeRoom='" + officeRoom + '\'' +
                ", salary=" + salary +
                ", employeeDate=" + employeeDate +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", emil='" + emil + '\'' +
                '}';
    }
}
