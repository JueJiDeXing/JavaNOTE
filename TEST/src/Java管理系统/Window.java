package Java管理系统;

import java.util.List;

public class Window {

    static void showMenu() {
        System.out.println("-----------------------");
        System.out.println("1. 显示所有学生信息");
        System.out.println("2. 查找学生信息");
        System.out.println("3. 添加学生信息");
        System.out.println("4. 退出");
    }

    static void showStudents(List<Student> students) {
        System.out.println("学号\t姓名\t性别\t生日");
        for (Student student : students) {
            System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getSex() + "\t" + student.getBirthday());
        }
    }

    static void showFindMethod() {
        System.out.println("请选择查找方式");
        System.out.println("1. 通过学号查找");
        System.out.println("2. 通过姓名查找");
        System.out.println("3. 通过性别查找");
        System.out.println("4. 通过生日查找");
    }



}
