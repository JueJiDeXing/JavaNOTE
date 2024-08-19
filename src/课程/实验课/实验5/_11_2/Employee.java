package 课程.实验课.实验5._11_2;

/**
 员工
 */
public class Employee extends Person {

    String officeRoom;//办公室
    int salary;//工资
    MyDate employeeDate;//受聘日期

    public Employee() {
    }

    public Employee(String name, String address, String phoneNum, String emil, String officeRoom, int salary, MyDate employeeDate) {
        super(name, address, phoneNum, emil);
        this.officeRoom = officeRoom;
        this.salary = salary;
        this.employeeDate = employeeDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "officeRoom='" + officeRoom + '\'' +
                ", salary=" + salary +
                ", employeeDate=" + employeeDate +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", emil='" + emil + '\'' +
                '}';
    }
}

