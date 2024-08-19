import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

record Student(String id, String name,
               String gender, LocalDate birthDate) {
    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}

class StudentManSys {
    List<Student> students;

    public StudentManSys() {
        students = new ArrayList<>();
        initStudents();
    }

    public void initStudents() {
        String[] fNames = "赵 钱 孙 李 周 武 王 魏".split(" ");
        String[] gNames = "⽇ ⽉ 之 ⾏ 若 出 其 中".split(" ");
        var rand = new Random();
        var now = LocalDate.now();
        var formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        for (int i = 0; i < 20; i++) {
            String id = "600" + now.format(formatter) + String.format("%02d", i + 1);
            String name = fNames[rand.nextInt(fNames.length)] + gNames[rand.nextInt(gNames.length)];
            String sex = rand.nextBoolean() ? "男" : "⼥";
            LocalDate birthDate = LocalDate.of(rand.nextInt(2000, 2010), rand.nextInt(1, 13), rand.nextInt(1, 29));
            Student e = new Student(id, name, sex, birthDate);
            students.add(e);
        }
    }

    private String formatStudentInfo(Student s) {
        return String.format("%14s%8s%6s%3d", s.id(), s.name(), s.gender(), s.getAge());
    }

    public void mainMenu() {
        String menu = """
                1. 显示所有学⽣信息
                2. 排序显示学⽣信息
                3. 查找学⽣信息
                4. 退出应⽤
                """;
        System.out.println(menu);
    }

    public void sortMenu() {
        String menu = """
                0. 返回上级菜单
                1. 按学号排序
                2. 按姓名排序
                3. 按性别排序
                4. 按年龄排序
                """;
        System.out.println(menu);
    }

    public void searchMenu() {
        String menu = """
                0. 返回上级菜单
                1. 按学号查找
                2. 按姓名查找
                3. 按性别查找
                4. 按年龄查找
                """;
        System.out.println(menu);
    }

    Scanner sc = new Scanner(System.in);

    public void operate() {
        do {
            mainMenu();
            System.out.print("请输⼊选择:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> opByCriteria();// 显示所有学生信息, (无条件)
                case 2 -> sortOp();// 排序显示
                case 3 -> searchOp();// 查找学生
                case 4 -> {
                    System.out.println("EXIT");
                    System.exit(0);
                }
                default -> System.out.println("输⼊错误,请重新输⼊!");
            }
        } while (true);
    }

    public void sortOp() {// 排序
        do {
            sortMenu();
            System.out.print("请输⼊选择:");
            int choice = sc.nextInt();
            switch (choice) {
                case 0 -> {return;}// 返回上级菜单
                // 按规则排序
                case 1 -> opByCriteria(Student::id);
                case 2 -> opByCriteria(Student::name);
                case 3 -> opByCriteria(Student::gender);
                case 4 -> opByCriteria(Student::birthDate);
                default -> System.out.println("输⼊错误,请重新输⼊!");
            }
        } while (true);
    }

    public void searchOp() {// 查找
        do {
            searchMenu();
            System.out.print("请输⼊选择:");
            int choice = sc.nextInt();
            switch (choice) {
                // 返回上级菜单
                case 0 -> {return;}
                // 按规则查找
                case 1 -> opByID();
                case 2 -> opByName();
                case 3 -> opByGender();
                case 4 -> opByAge();
                default -> System.out.println("输⼊错误,请重新输⼊!");
            }
        } while (true);
    }

    public void opByID() {
        System.out.print("请输⼊需要查询的学号:");
        String id = sc.next();
        opByCriteria(s -> s.id().equals(id), Student::id);
    }

    public void opByName() {
        System.out.print("请输⼊需要查询的姓名:");
        String name = sc.next();
        opByCriteria(s -> s.name().equals(name), Student::name);
    }

    public void opByGender() {
        System.out.print("请输⼊性别:");
        String gender = sc.next();
        opByCriteria(s -> s.gender().equals(gender), Student::id);
    }

    public void opByAge() {
        System.out.print("请输⼊年龄范围(例如 10 20):");
        int startAge = sc.nextInt(), endAge = sc.nextInt();
        opByCriteria(s -> startAge <= s.getAge() && s.getAge() <= endAge, Student::getAge);
    }

    /**
     @param tester       查询条件
     @param keyExtractor 排序方法
     @param mapper       对象-字符串转换格式
     @param op           对每个对象的操作(打印)
     */
    public void opByCriteria(
            Predicate<Student> tester,
            Function<Student, Comparable> keyExtractor,
            Function<Student, String> mapper,
            Consumer<String> op) {
        students.stream()
                .filter(tester)
                .sorted(Comparator.comparing(keyExtractor))
                .map(mapper)
                .forEach(op);
    }

    public void opByCriteria(
            Predicate<Student> tester,
            Function<Student, Comparable> keyExtractor) {
        // 有过滤规则, 有排序规则, 打印
        opByCriteria(tester, keyExtractor,
                this::formatStudentInfo, System.out::println);
    }

    public void opByCriteria(Function<Student, Comparable> keyExtractor) {
        // 有排序规则, 打印所有
        opByCriteria(__ -> true, keyExtractor, this::formatStudentInfo, System.out::println);
    }

    public void opByCriteria() {
        // 无排序规则, 无过滤规则, 打印所有
        opByCriteria(__ -> true, __ -> true, this::formatStudentInfo, System.out::println);
    }

    public static void main(String[] args) {
        StudentManSys system = new StudentManSys();
        system.operate();
    }
}
