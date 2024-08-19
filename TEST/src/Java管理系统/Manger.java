package Java管理系统;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Manger {
    static Random random = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<Student> students = randomStudent(20);
        while (true) {
            Window.showMenu();
            int choice = getInputNumber(Manger::illegalChoice);
            if (choice == Choice.EXIT.choice) break;
            doSth(choice, students);
        }
    }

    private static void doSth(int choice, List<Student> students) {
        if (choice == Choice.SHOW_ALL.choice) {//查询所有
            Window.showStudents(students);
            return;
        }
        if (choice == Choice.FIND_STUDENT.choice) {//查找
            Window.showFindMethod();
            int findMethod = getInputNumber(Manger::illegalMethod);
            System.out.println("请输入查找值");
            String findValue = sc.next();
            try {
                List<Student> result = switch (findMethod) {
                    case 1 -> find(students, s -> s.getId().equals(findValue));
                    case 2 -> find(students, s -> s.getName().equals(findValue));
                    case 3 -> find(students, s -> s.getSex().equals(findValue));
                    case 4 -> find(students, s -> s.getBirthday().equals(findValue));
                    default -> throw new IllegalStateException("Unexpected value: " + findMethod);
                };
                Window.showStudents(result);
            } catch (StudentNotFoundException e) {
                System.out.println("没有找到学生");
            }
            return;
        }
        if (choice == Choice.ADD_STUDENT.choice) {
            System.out.println("请输入学生信息(学号 姓名 性别 生日)");
            Student student = new Student(sc.next(), sc.next(), sc.next(), sc.next());
            try {
                find(students, s -> s.getId().equals(student.getId()));
                System.out.println("学号重复, 添加失败");
            } catch (StudentNotFoundException e) {
                students.add(student);
                System.out.println("添加成功");
            }
        }
        // impossible
    }


    private static int getInputNumber(Illegal illegal) {
        int choice = sc.nextInt();
        while (illegal.test(choice)) {
            System.out.println("输入不合法, 请重新输入");
            choice = sc.nextInt();
        }
        return choice;
    }

    static boolean illegalChoice(int choice) {
        return choice < 1 || choice > 4;
    }

    static boolean illegalMethod(int method) {
        return method < 1 || method > 4;
    }


    static List<Student> find(List<Student> students, FindRule findRule) throws StudentNotFoundException {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (findRule.find(student)) {
                result.add(student);
            }
        }
        if (result.isEmpty()) {
            throw new StudentNotFoundException("未找到该学生");
        }
        return result;
    }


    static List<Student> randomStudent(int n) {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String id = "" + i;
            String name = "学生" + i;
            String sex = random.nextBoolean() ? "男" : "女";
            String birthday = (random.nextInt(5) + 2000) + "-" + random.nextInt(1, 13) + "-" + random.nextInt(1, 30);
            students.add(new Student(id, name, sex, birthday));
        }
        return students;
    }
}
