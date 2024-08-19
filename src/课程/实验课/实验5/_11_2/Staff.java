package 课程.实验课.实验5._11_2;

import lombok.AllArgsConstructor;

/**
 职员
 */
@AllArgsConstructor
public class Staff extends Employee {
    String jobTitle;//职称

    public Staff(String name, String address, String phoneNum, String emil, String officeRoom, int salary, MyDate employeeDate, String jobTitle) {
        super(name, address, phoneNum, emil, officeRoom, salary, employeeDate);
        this.jobTitle = jobTitle;
    }

    public Staff() {
    }

    @Override
    public String toString() {
        return "Staff{" +
                "jobTitle='" + jobTitle + '\'' +
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
