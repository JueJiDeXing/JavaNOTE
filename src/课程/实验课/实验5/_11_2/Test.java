package 课程.实验课.实验5._11_2;

/**
 测试
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Person person = new Person("张三", "北京", "13100001111", "abc@163.com");
        Student student = new Student("李四", "上海", "13100002222", "bcd@163.com", Student.ONE);
        Employee employee = new Employee("王五", "广州", "13100003333", "cde@163.com", "1234567890", 10000, new MyDate());
        Faculty faculty = new Faculty("赵六", "深圳", "13100004444", "def@163.com", "信工楼A101", 10000, new MyDate(), 8, "教授");
        Staff staff = new Staff("钱七", "杭州", "13100005555", "efg@163.com", "信息楼B202", 10000, new MyDate(), "经理");
        System.out.println(person);
        System.out.println(student);
        System.out.println(employee);
        System.out.println(faculty);
        System.out.println(staff);
    }
}
